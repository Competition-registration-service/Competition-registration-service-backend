package ru.vsu.cs.sakovea.api.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.models.Competition;
import ru.vsu.cs.sakovea.models.Contestant;
import ru.vsu.cs.sakovea.models.FieldValue;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TeamDto {

    private int id;

    private String name;

    private String  accessCode;

    private Date createDate;

    private Date changeDate;

    private Competition competition;

    private Contestant creator;

    private List<Contestant> contestants;

    private List<FieldValue> fieldValues;
}
