package ru.vsu.cs.sakovea.api.dto.refvalue;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RefValueResponseDto {

    private int id;
    private String domainCid;
    private String valueCid;
    private String shortValue;
    private String longValue;
    private boolean visible;
    private String comment;
}
