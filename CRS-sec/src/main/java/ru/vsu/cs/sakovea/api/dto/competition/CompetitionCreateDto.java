package ru.vsu.cs.sakovea.api.dto.competition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.FieldDto;
import ru.vsu.cs.sakovea.api.dto.FileDto;
import ru.vsu.cs.sakovea.api.dto.TeamDto;
import ru.vsu.cs.sakovea.api.dto.UserCompPermDto;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.api.dto.contestant.ContestantDto;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueDto;

import java.sql.Timestamp;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CompetitionCreateDto {

    private int id;
    private String name;
    private Integer maxNumOfTeamMem;
    private Integer minNumOfTeamMem;
    private Timestamp startDate;
    private Timestamp endDate;
    private String cid;
    private RefValueDto refComp;
    private RefValueDto refCompCount;
    private RefValueDto refCompAge;
    private String competitionContent;
}
