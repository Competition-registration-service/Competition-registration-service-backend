package ru.vsu.cs.sakovea.api.dto;

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
public class ContentDto {
    private int id;
    private String content;
    private Competition competition;
    private RefValue refPage;
    private RefValue refFormat;
    private RefValue refLanguage;
}
