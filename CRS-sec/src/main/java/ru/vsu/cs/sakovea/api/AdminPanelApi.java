package ru.vsu.cs.sakovea.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.api.dto.user.UserDto;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;

import java.util.List;


@RequestMapping("/admin-panel")
@Tag(name = "Контроллер админ-панели", description = "Управление пользователями и соревнованиями")
public interface AdminPanelApi {

    String defaultOffset = "0";
    String defaultLimit = "25";

    @Operation(
            summary = "Создание соревнования(мероприятия)",
            description = "Создает мероприятие если parentId == null и если нет, то создает соревнование"
    )
    @PostMapping("/competition/create")
    ResponseEntity<?> createCompetition(HttpServletResponse response,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails,
                                       @RequestBody CompetitionDto competitionDto);

    @Operation(
            summary = "Обновление соревнования(мероприятия)",
            description = "Обновляет информацию мероприятия если parentId == null и если нет, то соревнования"
    )
    @PutMapping("/competition/update")
    ResponseEntity<CompetitionDto> updateCompetition(HttpServletResponse response,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails,
                                       @RequestBody CompetitionDto competitionDto);

    @Operation(
            summary = "Создание контента(страниц) мероприятия или соревнование",
            description = "Создает страницу или контент для мероприятия или соревнования"
    )
    @PostMapping("/competition/content/create")
    ResponseEntity<?> createCompetitionContent(HttpServletResponse response,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @RequestBody ContentDto contentDto);

    @Operation(
            summary = "Обновление контента(страниц) мероприятия или соревнование",
            description = "Обновляет страницу или контент для мероприятия или соревнования"
    )
    @PutMapping("/competition/content/update")
    ResponseEntity<ContentDto> updateCompetitionContent(HttpServletResponse response,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @RequestBody ContentDto contentDto);

    @Operation(
            summary = "Получение списка объявлений",
            description = "Возвращает список объявлений учебной организации (с пагинацией через query-параметры)"
    )
    @GetMapping("/users")
    ResponseEntity<List<UserDto>> getAllUsers(
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
}
