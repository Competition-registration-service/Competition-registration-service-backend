package ru.vsu.cs.sakovea.api.dto.content;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueDto;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseContentDto {

    private int id;
    private String feelingContent;
    private RefValueDto refPage;
    private RefValueDto refFormat;
    private RefValueDto refLanguage;

}
