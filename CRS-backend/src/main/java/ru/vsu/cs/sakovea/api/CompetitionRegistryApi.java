package ru.vsu.cs.sakovea.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sakovea.api.dto.*;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;

@Tag(name = "Контроллер регистрации на соревнование", description = "Операции регистрации на соревнование")
@RequestMapping("/competition-registry")
public interface CompetitionRegistryApi {

    @Operation(
            summary = "Регистрация на одиночное соревнование",
            description = "Регистрирует пользователя на одиночное соревнование"
    )
    @PostMapping("/{id}/contestant-register")
    ResponseEntity<ContestantDto> createContestant(
            @PathVariable ("id") int id,
            @RequestBody ContestantDto contestantDto);

    @Operation(
            summary = "Создание команды",
            description = "Создаёт команду"
    )
    @PostMapping("/{id}/create-team")
    ResponseEntity<TeamDto> createTeam(
            @RequestBody TeamDto teamDto
    );

    @Operation(
            summary = "Вход в команду",
            description = "Добавляет участника в команду"
    )
    @PutMapping("/team-competition/team/{team_id}/contestant")
    ResponseEntity<TeamDto> getIntoTeam(
            @PathVariable("team_id") int team_id,
            @RequestBody ContestantDto contestantDto);

}
