package ru.vsu.cs.sakovea.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueDto;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueResponseDto;
import ru.vsu.cs.sakovea.api.dto.team.GetTeamDto;
import ru.vsu.cs.sakovea.api.dto.team.GetTeamForCompetitionDto;
import ru.vsu.cs.sakovea.api.dto.team.TeamDto;
import ru.vsu.cs.sakovea.models.RefValue;
import ru.vsu.cs.sakovea.models.Team;

import java.util.List;

public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    TeamDto toTeamDto(Team team);

    @IterableMapping(elementTargetType = TeamDto.class)
    List<TeamDto> toTeamDtoList(List<Team> teams);

    GetTeamDto toGetTeamDto(Team team);

    @IterableMapping(elementTargetType = GetTeamDto.class)
    List<GetTeamDto> toGetTeamDtoList(List<Team> teams);

    GetTeamForCompetitionDto toGetTeamForCompetitionDto(Team team);

    @IterableMapping(elementTargetType = GetTeamForCompetitionDto.class)
    List<GetTeamForCompetitionDto> toGetTeamForCompetitionDtoList(List<Team> teams);

}
