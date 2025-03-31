package ru.vsu.cs.sakovea.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.sakovea.api.dto.contestant.ContestantDto;
import ru.vsu.cs.sakovea.api.dto.contestant.GetContestantDto;
import ru.vsu.cs.sakovea.api.dto.team.GetTeamDto;
import ru.vsu.cs.sakovea.api.dto.team.TeamDto;
import ru.vsu.cs.sakovea.models.Contestant;
import ru.vsu.cs.sakovea.models.Team;

import java.util.List;

public interface ContestantMapper {
    ContestantMapper INSTANCE = Mappers.getMapper(ContestantMapper.class);

    GetContestantDto toGetContestantDto(Contestant contestant);

    @IterableMapping(elementTargetType = GetContestantDto.class)
    List<GetContestantDto> toGetContestantDtoList(List<Contestant> contestants);

    ContestantDto toContestantDto(Contestant contestant);

    @IterableMapping(elementTargetType = ContestantDto.class)
    List<ContestantDto> toContestantDtoList(List<Contestant> contestants);

}
