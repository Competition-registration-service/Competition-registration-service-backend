package ru.vsu.cs.sakovea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.vsu.cs.sakovea.api.dto.file.FileDto;
import ru.vsu.cs.sakovea.api.dto.file.RequestFileDto;
import ru.vsu.cs.sakovea.exceptions.CustomException;
import ru.vsu.cs.sakovea.mapper.FilesMapper;
import ru.vsu.cs.sakovea.models.Competition;
import ru.vsu.cs.sakovea.models.Contestant;
import ru.vsu.cs.sakovea.models.File;
import ru.vsu.cs.sakovea.repository.CompetitionRepository;
import ru.vsu.cs.sakovea.repository.FileRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;


    private final CompetitionRepository competitionRepository;

    private final Path rootLocation = Paths.get("uploads");

    public FileDto saveFile(MultipartFile file, Integer competitionId, Integer id, RequestFileDto requestFileDto) {

        Competition event = competitionRepository.findById(id).orElse(null);
        if (event == null) {
            throw new CustomException("Такого мероприятия не существует!");
        } else {
            Competition competition = competitionRepository.findById(competitionId).get();
            if (event.getCompetitions().contains(competition)) {


                Path targetDir = rootLocation
                        .resolve("event/" + id)
                        .resolve("competition/" + competitionId);
                Files.createDirectories(targetDir);

                Path targetFile = targetDir.resolve(requestFileDto.getStorageFileId());

                Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);

                return "event/" + id + "/competition/" + competitionId + "/" + requestFileDto.getStorageFileId();

                File newFile = new File();
                newFile.setOrigFileName(file.getOriginalFilename());
                newFile.setStorageFileId(storageFileId);
                newFile.setCompetition(competition);


                File savedFile = fileRepository.save(newFile);

                return FilesMapper.INSTANCE.toFileDto(savedFile);
            }
            throw new CustomException("Соревнования не существует!");
        }
    }

    public Resource getFile(String storageFileId) {
        try {
            Path filePath = rootLocation.resolve(storageFileId).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("Файл не найден: " + storageFileId);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Ошибка загрузки файла: " + e.getMessage());
        }
        return fileStorageService.load(storageFileId);
    }

}
