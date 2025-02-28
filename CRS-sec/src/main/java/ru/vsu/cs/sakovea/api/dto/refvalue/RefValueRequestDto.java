package ru.vsu.cs.sakovea.api.dto.refvalue;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RefValueRequestDto {

    private int id;
    private String domainCid;
    private String valueCid;
}
