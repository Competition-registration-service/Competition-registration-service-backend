package ru.vsu.cs.sakovea.api.dto.field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RequestFieldDto {
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
    private RefValueDto refType;
}


