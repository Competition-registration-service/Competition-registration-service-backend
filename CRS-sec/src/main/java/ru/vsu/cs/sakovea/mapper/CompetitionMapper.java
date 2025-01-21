package ru.vsu.cs.sakovea.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.competition.GetCompetitionDto;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.models.Competition;
import ru.vsu.cs.sakovea.models.Content;

import java.util.List;

@Mapper
public interface CompetitionMapper {

    CompetitionMapper INSTANCE = Mappers.getMapper(CompetitionMapper.class);

    CompetitionDto toCompetitionDto(Competition competition);

    @IterableMapping(elementTargetType = CompetitionDto.class)
    List<CompetitionDto> toCompetitionDtoList(List<Competition> competitionList);

    @IterableMapping(elementTargetType = GetCompetitionDto.class)
    List<GetCompetitionDto> toGetCompetitionDtoList(List<Competition> competitionList);

    Competition toCompetition(CompetitionDto competitionDto);

    @IterableMapping(elementTargetType = Competition.class)
    List<Competition> toCompetitionList(List<CompetitionDto> competitionDtoList);
}
