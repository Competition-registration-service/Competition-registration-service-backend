package ru.vsu.cs.sakovea.api.dto.competition;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.api.dto.content.ResponseContentDto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EventDto {
    private int id;
    private String name;
    private Timestamp startDate;
    private Timestamp endDate;
    private String cid;
    private List<GetCompetitionDto> competitions;
    private List<ResponseContentDto> contents;
}