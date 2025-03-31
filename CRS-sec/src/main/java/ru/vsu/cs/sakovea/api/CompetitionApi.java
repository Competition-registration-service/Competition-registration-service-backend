package ru.vsu.cs.sakovea.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
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

    String defaultOffset = "0";
    String defaultLimit = "25";

    // TODO получение списка участников и команд по комп айди, и тогда убрать списки из гетКомпДто

    @Operation(
            summary = "Получение мероприятия",
            description = "Возвращает мероприятие по айди"
    )
    @GetMapping("/{id}")
    ResponseEntity<?> getEvent(@PathVariable("id") Integer id, @AuthenticationPrincipal UserDetailsImpl userDetails);

    @Operation(
            summary = "Получение соревнования",
            description = "Возвращает выбранное соревнование"
    )
    @GetMapping("/{eventId}/competition/{id}")
    ResponseEntity<?> getCompetition(@PathVariable("eventId") Integer eventId, @PathVariable("id") Integer id,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails);

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
            summary = "Регистрация на одиночное соревнование",
            description = "Регистрирует участника на одиночное соревнование"
    )
    @PostMapping("/{id}/competition/{competitionId}/registration-page")
    ResponseEntity<?> registerOnSingleCompetition(@PathVariable("id") Integer id,
                                                  @PathVariable("competitionId") Integer competitionId,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                  @RequestBody List<RequestFieldValueDto> requestFieldValueDto);

    @Operation(
            summary = "Проверка кода доступа к команде",
            description = "Проверяет код доступа к команде"
    )
    @PostMapping("/{id}/competition/{competitionId}/registration-page/{team-code}/confirm")
    ResponseEntity<?> checkTeamAccessCode(@PathVariable("id") Integer id,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails,
                                          @PathVariable("competitionId") Integer competitionId,
                                          @PathVariable("team-code") String teamCode);


    @Operation(
            summary = "Регистрация на командное соревнование",
            description = "Регистрирует участника на командное соревнование"
    )
    @PostMapping("/{id}/competition/{competitionId}/registration-page/team")
    ResponseEntity<?> registerOnTeamCompetition(@PathVariable("id") Integer id,
                                                @PathVariable("competitionId") Integer competitionId,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                @RequestBody List<RequestFieldValueDto> requestFieldValueDto);

    @Operation(
            summary = "Регистрация капитана на командное соревнование",
            description = "Регистрирует капитана на командное соревнование"
    )
    @PostMapping("/{id}/competition/{competitionId}/registration-page/team/capitan")
    ResponseEntity<?> capitanRegisterOnTeamCompetition(@PathVariable("id") Integer id,
                                                       @PathVariable("competitionId") Integer competitionId,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                       @RequestBody List<RequestFieldValueDto> requestFieldValueDto);

//    @Operation(
//            summary = "Получение списка соревнований и мероприятий которые уже были",
//            description = "Возвращает список соревнований и мероприятий которые уже были"
//    )
//    @GetMapping("/competitions/history")
//    ResponseEntity<?> getHistoryOfEventAndCompetitions(@AuthenticationPrincipal UserDetailsImpl userDetails,
//
//                                                       @Schema(description = "Номер страницы для пагинации", minimum = "0", defaultValue = defaultOffset)
//                                                       @RequestParam(name = "offset", defaultValue = defaultOffset)
//                                                       @Min(0)
//                                                       Integer offset,
//
//                                                       @Schema(description = "Количество мероприятий или соревнований" +
//                                                               " на странице для пагинации", minimum = "1", maximum = "50", defaultValue = defaultLimit)
//                                                       @RequestParam(name = "limit", defaultValue = defaultLimit)
//                                                       @Min(1) @Max(50)
//                                                       Integer limit);
}
