package ru.vsu.cs.sakovea.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.api.dto.content.ResponseContentDto;
import ru.vsu.cs.sakovea.models.Content;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ContentMapper {

    ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);

    ContentDto toContentDto(Content content);

    ResponseContentDto toResponseContentDto(Content content);

    ContentDto toContentDto(Optional<Content> content);

    @IterableMapping(elementTargetType = ContentDto.class)
    List<ContentDto> toContentDtoList(List<Content> contentList);

    @IterableMapping(elementTargetType = ContentDto.class)
    List<ResponseContentDto> toResponseContentDtoList(List<Content> contentList);

    Content toContent(ContentDto contentDto);

    @IterableMapping(elementTargetType = Content.class)
    List<Content> toContentList(List<ContentDto> contentDtoList);
}
