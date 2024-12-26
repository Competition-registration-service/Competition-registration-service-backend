package ru.vsu.cs.sakovea.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.contestant.ContestantDto;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TeamDto {

    private int id;
    private String name;
    private String accessCode;
    private Date createDate;
    private Date changeDate;
    private List<ContestantDto> contestants;
    private CompetitionDto competition;
}
