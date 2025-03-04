package ru.vsu.cs.sakovea.api.dto.competition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueDto;

import java.sql.Timestamp;


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
