package ru.vsu.cs.sakovea.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueDto;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueResponseDto;
import ru.vsu.cs.sakovea.models.Competition;
import ru.vsu.cs.sakovea.models.RefValue;

import java.util.List;

@Mapper
public interface RefValueMapper {
    RefValueMapper INSTANCE = Mappers.getMapper(RefValueMapper.class);

    RefValueDto toRefValueDto(RefValue refValue);

    @IterableMapping(elementTargetType = RefValueDto.class)
    List<RefValueDto> toRefValueDtoList(List<RefValue> refValueList);

    RefValueResponseDto toRefValueResponseDto(RefValue refValue);

    @IterableMapping(elementTargetType = RefValueResponseDto.class)
    List<RefValueResponseDto> toRefValueResponseDtoList(List<RefValue> refValueList);

    RefValue toRefValue(RefValueDto refValueDto);

    @IterableMapping(elementTargetType = RefValue.class)
    List<RefValue> toRefValueList(List<RefValueDto> refValueDtoList);
}

