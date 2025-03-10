package ru.vsu.cs.sakovea.api.dto.field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.FieldValueDto;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueDto;

import java.util.List;

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
    private int orderr;
    private boolean optional;
    private CompetitionDto competition;
    private RefValueDto refType;
    private List<FieldValueDto> fieldValues;
}
