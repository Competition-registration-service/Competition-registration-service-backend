package ru.vsu.cs.sakovea.api.dto.competition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.*;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.api.dto.contestant.ContestantDto;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueDto;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CompetitionDto {

    private int id;
    private String name;
    private int maxNumOfTeamMem;
    private int minNumOfTeamMem;
    private Date startDate;
    private Date endDate;
    private String cid;
    private CompetitionDto parent;
    private List<CompetitionDto> competitions;
    private RefValueDto refComp;
    private RefValueDto refCompCount;
    private RefValueDto refCompAge;
    private String competitionContent;
    private List<ContentDto> contents;
    private List<ContestantDto> contestants;
    private List<FieldDto> fields;
    private List<FileDto> files;
    private List<TeamDto> teams;
    private List<UserCompPermDto> roles;
}
