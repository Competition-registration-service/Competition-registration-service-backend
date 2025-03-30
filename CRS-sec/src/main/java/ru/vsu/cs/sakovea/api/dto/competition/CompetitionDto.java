package ru.vsu.cs.sakovea.api.dto.competition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.*;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.api.dto.contestant.ContestantDto;
import ru.vsu.cs.sakovea.api.dto.field.FieldDto;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueDto;
import ru.vsu.cs.sakovea.api.dto.team.TeamDto;
import ru.vsu.cs.sakovea.api.dto.userCompPerms.UserCompPermDto;

import java.sql.Timestamp;
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
    private Timestamp startDate;
    private Timestamp endDate;
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
