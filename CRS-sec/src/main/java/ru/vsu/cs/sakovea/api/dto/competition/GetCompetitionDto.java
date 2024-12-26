package ru.vsu.cs.sakovea.api.dto.competition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.TeamDto;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.api.dto.contestant.ContestantDto;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueDto;

import java.util.Date;
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
    private Date startDate;
    private Date endDate;
    private String cid;
    private RefValueDto refComp;
    private RefValueDto refCompCount;
    private RefValueDto refCompAge;
    private List<ContentDto> contents;
    private List<ContestantDto> contestants;
    private List<TeamDto> teams;
}


