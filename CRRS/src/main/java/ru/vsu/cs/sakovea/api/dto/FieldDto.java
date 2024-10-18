package ru.vsu.cs.sakovea.api.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.models.Competition;
import ru.vsu.cs.sakovea.models.RefValue;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FieldDto {

    private int id;

    private String selectDomainCid;

    private boolean teamField;

    private String cid;

    private String shortName;

    private String longName;

    private String comment;

    private String exampleValue;

    private int maxLength;

    private int order;

    private boolean optional;

    private Competition competition;

    private RefValue refType;
}
