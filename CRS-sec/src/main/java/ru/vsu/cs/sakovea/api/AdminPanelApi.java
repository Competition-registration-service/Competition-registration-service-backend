package ru.vsu.cs.sakovea.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionCreateDto;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.competition.CreateEventDto;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.api.dto.content.RequestContentDto;
import ru.vsu.cs.sakovea.api.dto.field.CreateFieldDto;
import ru.vsu.cs.sakovea.api.dto.user.ChangeUserRoleDto;
import ru.vsu.cs.sakovea.api.dto.user.GetUserDto;
import ru.vsu.cs.sakovea.api.dto.user.GetUserForAdminDto;
import ru.vsu.cs.sakovea.api.dto.user.UserDto;
import ru.vsu.cs.sakovea.models.Competition;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;

import java.util.List;


@RequestMapping("/admin-panel")
@Tag(name = "Контроллер админ-панели ✅", description = "Управление пользователями и соревнованиями")
public interface AdminPanelApi {

    String defaultOffset = "0";
    String defaultLimit = "25";

    @Operation(
            summary = "Создание мероприятия",
            description = "Создает мероприятие"
    )
    @PostMapping("/event/create")
    ResponseEntity<?> createEvent(HttpServletResponse response,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails,
                                       @RequestBody CreateEventDto eventDto); // todo изменить на реквест ивент дто

    @Operation(
            summary = "Обновление мероприятия",
            description = "Обновляет информацию мероприятия"
    )
    @PutMapping("/event/update")
    ResponseEntity<?> updateEvent(HttpServletResponse response,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails,
                                       @RequestBody CompetitionDto competitionDto); // todo изменить на реквест ивент дто

    @Operation(
            summary = "Создание соревнования",
            description = "Создает соревнование"
    )
    @PostMapping("/event/{id}/create-competition")
    ResponseEntity<?> createCompetition(HttpServletResponse response,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @RequestBody CompetitionCreateDto competitionCreateDto,
                                        @PathVariable ("id") int eventId);

    @Operation(
            summary = "Обновление соревнования",
            description = "Обновляет информацию соревнования"
    )
    @PutMapping("/event/{id}/update-competition")
    ResponseEntity<?> updateCompetition(HttpServletResponse response,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @RequestBody CompetitionDto competitionDto,
                                                     @PathVariable ("id") int eventId);

    @Operation(
            summary = "Создание контента(страниц) мероприятия или соревнование",
            description = "Создает страницу или контент для мероприятия или соревнования"
    )
    @PostMapping("/event/{id}/content/create")
    ResponseEntity<?> createCompetitionContent(HttpServletResponse response,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @RequestBody RequestContentDto contentDto,
                                               @PathVariable ("id") int competitionId);

    @Operation(
            summary = "Обновление контента(страниц) мероприятия или соревнование",
            description = "Обновляет страницу или контент для мероприятия или соревнования"
    )
    @PutMapping("/event/{id}/content/update")
    ResponseEntity<?> updateCompetitionContent(HttpServletResponse response,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @RequestBody ContentDto contentDto,
                                                        @PathVariable ("id") int competitionId);

    @Operation(
            summary = "Получение списка пользователей",
            description = "Возвращает список пользователей (с пагинацией через query-параметры)"
    )
    @GetMapping("/users")
    ResponseEntity<?> getAllUsers(
            @AuthenticationPrincipal UserDetailsImpl userDetails,

            @Schema(description = "Номер страницы для пагинации", minimum = "0", defaultValue = defaultOffset)
            @RequestParam(name = "offset", defaultValue = defaultOffset)
            @Min(0)
            Integer offset,

            @Schema(description = "Количество пользователей на странице для пагинации", minimum = "1", maximum = "50", defaultValue = defaultLimit)
            @RequestParam(name = "limit", defaultValue = defaultLimit)
            @Min(1) @Max(50)
            Integer limit
    );

    @Operation(
            summary = "Получение пользователя",
            description = "Возвращает информацию о пользователе"
    )
    @GetMapping("/user/{id}")
    ResponseEntity<?> getUserForAdmin(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                               @PathVariable ("id") int userId);


    @Operation(
            summary = "Изменение роли пользователя",
            description = "Изменяет роль пользователя"
    )
    @PutMapping("/user/{id}")
    ResponseEntity<?> changeUserRole(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                            @PathVariable ("id") int userId,
                                           @RequestBody ChangeUserRoleDto changeUserRoleDto);



    @Operation(
            summary = "Создание формы регистрации на соревнования",
            description = "Создает форму регистрации на соревнования"
    )
    @PostMapping("/event/{id}/competition/{competitionId}/create-registration-page")
    ResponseEntity<?> createCompetitionRegistrationPage(HttpServletResponse response,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @RequestBody CreateFieldDto createFieldDto,
                                                        @PathVariable("id") Integer id,
                                                        @PathVariable("competitionId") Integer competitionId);


    @Operation(
            summary = "Получение словаря",
            description = "Возвращает информацию о словаре"
    )
    @GetMapping("/ref-values")
    ResponseEntity<?> getRefValuesAdmin(@AuthenticationPrincipal UserDetailsImpl userDetails);
}
