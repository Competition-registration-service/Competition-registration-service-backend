package ru.vsu.cs.sakovea.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.refvalue.RefValueDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FileDto {

    private int id;
    private String origFileName;
    private String storageFileId;
    private CompetitionDto competition;
    private RefValueDto refFile;
}
