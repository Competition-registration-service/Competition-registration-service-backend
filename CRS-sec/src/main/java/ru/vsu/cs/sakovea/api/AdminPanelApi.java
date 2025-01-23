package ru.vsu.cs.sakovea.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vsu.cs.sakovea.api.dto.competition.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.content.ContentDto;
import ru.vsu.cs.sakovea.api.dto.user.UserDto;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;


@RequestMapping("/admin-panel")
@Tag(name = "Контроллер админ-панели", description = "Управление пользователями и соревнованиями")
public interface AdminPanelApi {

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
}
