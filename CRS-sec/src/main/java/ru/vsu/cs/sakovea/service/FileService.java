package ru.vsu.cs.sakovea.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.file.FileDto;
import ru.vsu.cs.sakovea.api.dto.file.RequestFileDto;
import ru.vsu.cs.sakovea.api.dto.file.ResponseFileDto;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueDto;
import ru.vsu.cs.sakovea.exceptions.CustomException;
import ru.vsu.cs.sakovea.mapper.FilesMapper;
import ru.vsu.cs.sakovea.mapper.RefValueMapper;
import ru.vsu.cs.sakovea.models.*;
import ru.vsu.cs.sakovea.repository.CompetitionRepository;
import ru.vsu.cs.sakovea.repository.FileRepository;
import ru.vsu.cs.sakovea.repository.RefValueRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    private final FileRepository fileRepository;
    private final CompetitionRepository competitionRepository;
    private final RefValueRepository refValueRepository;
    private final Path fileStorageLocation;

    @Autowired
    public FileService(
            FileRepository fileRepository,
            CompetitionRepository competitionRepository,
            RefValueRepository refValueRepository,
            @Value("${file.upload-dir:uploads}") String uploadDir
    ) {
        this.fileRepository = fileRepository;
        this.competitionRepository = competitionRepository;
        this.refValueRepository = refValueRepository;
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            throw new RuntimeException("Could not create upload directory", ex);
        }
    }

    private void checkIsUserAdmin(UserDetailsImpl userDetails) {
        if (Boolean.TRUE.equals(userDetails.getUser().isAdmin()) || (userDetails.getUser().getRoles().getFirst().
                getRefRole().getValueCid().equals(refValueRepository.findRefValueByValueCid("ADMIN").getValueCid()))) {
            return;
        }
        throw new CustomException("Доступ запрещён");
    }

    private RequestFileDto convertStringToDto(String stringFileDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(stringFileDto, RequestFileDto.class);
        } catch (IOException ex) {
            throw new CustomException("Invalid fileDto format: " + ex.getMessage());
        }
    }

    public ResponseFileDto uploadFile(Integer eventId, Integer competitionId, MultipartFile file, UserDetailsImpl userDetails, String fileDto) {
        checkIsUserAdmin(userDetails);

        RequestFileDto requestFileDto = convertStringToDto(fileDto);

        try {
            Competition competition = competitionRepository.findById(competitionId)
                    .orElseThrow(() -> new CustomException("Competition not found"));

            String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            String storageFileName = StringUtils.cleanPath(requestFileDto.getStorageFileId());
            if (storageFileName.isBlank() || !storageFileName.matches("[a-zA-Z0-9._-]+")) {
                logger.error("Invalid storageFileName: {}", storageFileName);
                throw new CustomException("Invalid storageFileId: must be non-empty and contain only letters, numbers, dots, or hyphens");
            }
            String storageFileId = eventId + "_" + storageFileName;
            String filePath = eventId + "/" + storageFileName;

            if (fileRepository.findByStorageFileIdAndCompetitionId(storageFileId, competitionId).isPresent()) {
                logger.error("File with storageFileId {} already exists for competition {}", storageFileId, competitionId);
                throw new CustomException("File with name " + storageFileName + " already exists for this competition");
            }

            logger.info("Uploading file: originalFileName={}, storageFileId={}, filePath={}",
                    originalFileName, storageFileId, filePath);

            Path targetLocation = this.fileStorageLocation.resolve(filePath);
            Files.createDirectories(targetLocation.getParent());
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            File fileEntity = new File();
            fileEntity.setOrigFileName(originalFileName);
            fileEntity.setStorageFileId(storageFileId);
            fileEntity.setCompetition(competition);
            if (requestFileDto.getRefValueDto() != null) {
                fileEntity.setRefFile(RefValueMapper.INSTANCE.toRefValue(requestFileDto.getRefValueDto()));
            }

            fileEntity = fileRepository.save(fileEntity);
            logger.info("File saved in database: id={}, storageFileId={}", fileEntity.getId(), storageFileId);

            return ResponseFileDto.builder()
                    .id(fileEntity.getId())
                    .origFileName(fileEntity.getOrigFileName())
                    .storageFileId(fileEntity.getStorageFileId())
                    .competitionId(competition.getId())
                    .refFileId(requestFileDto.getRefValueDto() != null ? requestFileDto.getRefValueDto().getId() : null)
                    .build();

        } catch (IOException ex) {
            logger.error("Failed to store file: {}, error: {}", file.getOriginalFilename(), ex.getMessage());
            throw new CustomException("Could not store file: " + file.getOriginalFilename() + " /../ " + ex);
        }
    }

    public List<FileDto> getCompetitionFiles(Integer competitionId, UserDetailsImpl userDetails) {
        Competition competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new RuntimeException("Competition not found"));

        List<File> files = fileRepository.findByCompetitionId(competitionId);
        return files.stream()
                .map(file -> FileDto.builder()
                        .id(file.getId())
                        .origFileName(file.getOrigFileName())
                        .storageFileId(file.getStorageFileId())
                        .competition(CompetitionDto.builder().id(file.getCompetition().getId()).build())
                        .refFile(file.getRefFile() != null ? RefValueDto.builder().id(file.getRefFile().getId()).build() : null)
                        .build())
                .collect(Collectors.toList());
    }

    public Resource downloadFile(Integer competitionId, String storageFileId, UserDetailsImpl userDetails) {
        logger.info("Downloading file: competitionId={}, storageFileId={}", competitionId, storageFileId);

        Competition competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new CustomException("Competition not found"));

        String decodedStorageFileId = StringUtils.uriDecode(storageFileId, StandardCharsets.UTF_8);
        logger.debug("Decoded storageFileId: {}", decodedStorageFileId);

        File fileEntity = fileRepository.findByStorageFileId(decodedStorageFileId);
        if (fileEntity == null) {
            List<File> files = fileRepository.findByCompetitionId(competitionId);
            List<String> availableFileIds = files.stream()
                    .map(File::getStorageFileId)
                    .collect(Collectors.toList());
            logger.error("File not found in database: {}. Available storageFileIds for competition {}: {}",
                    decodedStorageFileId, competitionId, availableFileIds);
            throw new CustomException("File not found: " + decodedStorageFileId);
        }

        if (fileEntity.getCompetition().getId() != competitionId) {
            logger.error("File {} does not belong to competition {}", decodedStorageFileId, competitionId);
            throw new CustomException("File does not belong to this competition");
        }

        String filePath;
        if (decodedStorageFileId.contains("_")) {
            String[] parts = decodedStorageFileId.split("_", 2);
            if (parts.length != 2) {
                logger.error("Invalid storageFileId structure: {}", decodedStorageFileId);
                throw new CustomException("Invalid storageFileId structure: " + decodedStorageFileId);
            }
            String eventId = parts[0];
            String storageFileName = parts[1];
            filePath = eventId + "/" + storageFileName;
        } else {
            logger.warn("Legacy storageFileId format detected: {}", decodedStorageFileId);
            filePath = decodedStorageFileId;
        }

        try {
            Path targetLocation = this.fileStorageLocation.resolve(filePath).normalize();
            logger.debug("Resolved file path: {}", targetLocation);

            Resource resource = new UrlResource(targetLocation.toUri());
            if (resource.exists() && resource.isReadable()) {
                logger.info("File found and readable: {}", filePath);
                return resource;
            } else {
                logger.error("File not found or not readable on disk: {}", filePath);
                throw new CustomException("File not found or not readable: " + decodedStorageFileId);
            }
        } catch (MalformedURLException ex) {
            logger.error("Malformed URL for file: {}, error: {}", filePath, ex.getMessage());
            throw new CustomException("File not found: " + decodedStorageFileId + " /../ " + ex);
        }
    }
}