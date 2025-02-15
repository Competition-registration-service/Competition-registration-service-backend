package ru.vsu.cs.sakovea.api.dto.competition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;

import java.sql.Timestamp;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateEventDto {

    private int id;
    private String name;
    private Timestamp startDate;
    private Timestamp endDate;
    private String cid;
}
