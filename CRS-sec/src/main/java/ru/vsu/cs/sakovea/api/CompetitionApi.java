package ru.vsu.cs.sakovea.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.competition.EventDto;
import ru.vsu.cs.sakovea.api.dto.competition.GetCompetitionDto;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.api.dto.content.ResponseContentDto;
import ru.vsu.cs.sakovea.api.dto.user.UserDto;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;

import java.util.List;

@RequestMapping("/event")
@Tag(name = "Контроллер соревнований и мероприятий", description = "Управление мероприятиями и соревнованиями")
public interface CompetitionApi {

    @Operation(
            summary = "Получение мероприятия",
            description = "Возвращает мероприятие по айди"
    )
    @GetMapping("/{id}")
    ResponseEntity<EventDto> getEvent(@PathVariable("id") Integer id);

    @Operation(
            summary = "Получение соревнования",
            description = "Возвращает выбранное соревнование"
    )
    @GetMapping("/{eventId}/competition/{id}")
    ResponseEntity<GetCompetitionDto> getCompetition(@PathVariable("eventId") Integer eventId, @PathVariable("id") Integer id );

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

    @Operation(
            summary = "Получение страниц или контента мероприятия",
            description = "Возвращает страницy или контент данного мероприятия"
    )
    @GetMapping("/{eventId}/content/{id}")
    ResponseEntity<ResponseContentDto> getEventContent(@PathVariable("eventId") Integer eventId, @PathVariable("id") Integer id);


    @Operation(
            summary = "Получение страниц или контента мероприятия",
            description = "Возвращает страницy или контент данного мероприятия"
    )
    @GetMapping("/{id}/contents")
    ResponseEntity<List<ResponseContentDto>> getEventContents(@PathVariable("id") Integer id);
}
