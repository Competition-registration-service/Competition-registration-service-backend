package ru.vsu.cs.sakovea.api.dto.field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.fieldvalue.FieldValueDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseFieldDto {
    private int id;
    private boolean teamField;
    private String shortName;
    private String longName;
    private String comment;
    private String exampleValue;
    private int maxLength;
    private boolean optional;
    private List<FieldValueDto> fieldValues;
}

