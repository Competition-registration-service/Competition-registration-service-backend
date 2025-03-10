package ru.vsu.cs.sakovea.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.contestant.ContestantDto;
import ru.vsu.cs.sakovea.api.dto.field.FieldDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FieldValueDto {

    private int id;
    private String value;
    private ContestantDto contestant;
    private TeamDto team;
    private FieldDto field;
}
