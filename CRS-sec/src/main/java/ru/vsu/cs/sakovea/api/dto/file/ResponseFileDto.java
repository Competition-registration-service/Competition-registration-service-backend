package ru.vsu.cs.sakovea.api.dto.file;

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
public class ResponseFileDto {

    private int id;
    private String origFileName;
    private String storageFileId;
    private Integer competitionId;
    private Integer refFileId;
}
  // + " /../ " +