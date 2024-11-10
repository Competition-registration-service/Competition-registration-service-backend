package ru.vsu.cs.sakovea.api.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.models.Contestant;
import ru.vsu.cs.sakovea.models.Field;
import ru.vsu.cs.sakovea.models.Team;

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
