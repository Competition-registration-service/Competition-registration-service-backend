package ru.vsu.cs.sakovea.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ru.vsu.cs.sakovea.api.dto.file.FileDto;
import ru.vsu.cs.sakovea.api.dto.file.RequestFileDto;
import ru.vsu.cs.sakovea.api.dto.file.ResponseFileDto;
import ru.vsu.cs.sakovea.models.File;

import java.util.List;

@Mapper
public interface FilesMapper {

    FilesMapper INSTANCE = Mappers.getMapper(FilesMapper.class);

    FileDto toFileDto(File file);

    ResponseFileDto toResponseFileDto(File file);

    @IterableMapping(elementTargetType = FileDto.class)
    List<FileDto> toFileDtoList(List<File> fileList);

    File toFile(FileDto fileDto);

    @IterableMapping(elementTargetType = File.class)
    List<File> toFileList(List<FileDto> fileDtoList);
}
