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
import ru.vsu.cs.sakovea.api.dto.field.ResponseFieldDto;
import ru.vsu.cs.sakovea.api.dto.fieldvalue.RequestFieldValueDto;
import ru.vsu.cs.sakovea.api.dto.user.UserDto;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;

import java.util.List;

@RequestMapping("/event")
@Tag(name = "Контроллер соревнований и мероприятий ✅", description = "Управление мероприятиями и соревнованиями")
public interface CompetitionApi {

    // TODO получение списка участников и команд по комп айди, и тогда убрать списки из гетКомпДто

    @Operation(
            summary = "Получение мероприятия",
            description = "Возвращает мероприятие по айди"
    )
    @GetMapping("/{id}")
    ResponseEntity<?> getEvent(@PathVariable("id") Integer id);

    @Operation(
            summary = "Получение соревнования",
            description = "Возвращает выбранное соревнование"
    )
    @GetMapping("/{eventId}/competition/{id}")
    ResponseEntity<?> getCompetition(@PathVariable("eventId") Integer eventId, @PathVariable("id") Integer id );

    @Operation(
            summary = "Получение списка соревнований",
            description = "Возвращает список соревнований данного мероприятия (с пагинацией через query-параметры)"
    )
    @GetMapping("/competitions")
    ResponseEntity<?> getAllCompetitions(
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
    ResponseEntity<?> getEventContent(@PathVariable("eventId") Integer eventId, @PathVariable("id") Integer id);


    @Operation(
            summary = "Получение страниц или контента мероприятия",
            description = "Возвращает страницy или контент данного мероприятия"
    )
    @GetMapping("/{id}/contents")
    ResponseEntity<?> getEventContents(@PathVariable("id") Integer id);

    @Operation(
            summary = "Получение формы регистрации на соревнование",
            description = "Возвращает форму регистрации на соревнование"
    )
    @GetMapping("/{id}/competition/{competitionId}/registration-page")
    ResponseEntity<?> getRegistrationPage(@PathVariable("id") Integer id,
                                                               @PathVariable("competitionId") Integer competitionId);

    @Operation(
            summary = "Регистрация на соревнование",
            description = "Регистрирует участника на соревнование"
    )
    @PostMapping("/{id}/competition/{competitionId}/registration-page")
    ResponseEntity<?> registerOnCompetition(@PathVariable("id") Integer id,
                                            @PathVariable("competitionId") Integer competitionId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails,
                                            @RequestBody List<RequestFieldValueDto> requestFieldValueDto);
}
