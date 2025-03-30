package ru.vsu.cs.sakovea.api.dto.competition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.team.TeamDto;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.api.dto.contestant.ContestantDto;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueDto;

import java.sql.Timestamp;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetCompetitionDto {
    private int id;
    private String name;
    private int maxNumOfTeamMem;
    private int minNumOfTeamMem;
    private Timestamp startDate;
    private Timestamp endDate;
    private String cid;
    private RefValueDto refComp;
    private RefValueDto refCompCount;
    private RefValueDto refCompAge;
    private List<ContentDto> contents;
    private List<ContestantDto> contestants;
    private List<TeamDto> teams;
}


