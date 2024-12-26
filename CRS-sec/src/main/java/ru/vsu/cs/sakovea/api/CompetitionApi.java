package ru.vsu.cs.sakovea.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.competition.EventDto;
import ru.vsu.cs.sakovea.api.dto.competition.GetCompetitionDto;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;

import java.util.List;

@RequestMapping("/event")
public interface CompetitionApi {

    @GetMapping("/{id}")
    ResponseEntity<EventDto> getEvent(@PathVariable("id") Integer id);

    @GetMapping("/competition/{id}")
    ResponseEntity<GetCompetitionDto> getCompetition(@PathVariable("id") Integer id);

    @Operation(
            summary = "Получение списка соревнований",
            description = "Возвращает список соревнований данного мероприятия (с пагинацией через query-параметры)"
    )
    @GetMapping("/competitions")
    ResponseEntity<List<GetCompetitionDto>> getAllCompetitions(
            @Schema(description = "Id мероприятия", minimum = "0")
            @RequestParam(name = "eventId", required = false)
            @Min(0)
            Integer eventId
    );

    @GetMapping("/content/{id}")
    ResponseEntity<ContentDto> getEventContent(@PathVariable("id") Integer id);
}
