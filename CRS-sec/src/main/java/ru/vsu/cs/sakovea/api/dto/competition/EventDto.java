package ru.vsu.cs.sakovea.api.dto.competition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EventDto {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String cid;
    private List<CompetitionDto> competitions;
    private List<ContentDto> contents;
}