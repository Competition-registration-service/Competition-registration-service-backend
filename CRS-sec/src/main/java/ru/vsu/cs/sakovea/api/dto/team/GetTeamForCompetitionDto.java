package ru.vsu.cs.sakovea.api.dto.team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.contestant.ContestantDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetTeamForCompetitionDto {

    private int id;
    private String name;
    private List<ContestantDto> contestants;
}
