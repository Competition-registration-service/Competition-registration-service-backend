package ru.vsu.cs.sakovea.api.dto.fieldvalue;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseFieldValueDto {
    private int id;
    private String value;
}
