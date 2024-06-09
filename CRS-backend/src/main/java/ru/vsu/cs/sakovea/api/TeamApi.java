package ru.vsu.cs.sakovea.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sakovea.api.dto.ContestantDto;
import ru.vsu.cs.sakovea.api.dto.FieldValueDto;
import ru.vsu.cs.sakovea.api.dto.TeamDto;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;

@Tag(name = "Контроллер команды", description = "Операции профодимые с командой")
@RequestMapping("/team")
public interface TeamApi {

    @Operation(
            summary = "Редактирование команды",
            description = "Обновляет команду"
    )
    @PutMapping("/{id}/update")
    ResponseEntity<TeamDto> updateTeam(
            @PathVariable("id") int id,
            @RequestBody TeamDto teamDto);

    @Operation(
            summary = "Редактирование роли капитана",
            description = "Обновляет роли капитана"
    )
    @PutMapping("/{id}/update")
    ResponseEntity<TeamDto> updateCapitan(
            @PathVariable("id") int id,
            @RequestBody TeamDto teamDto);

    @Operation(
            summary = "Удаление команды",
            description = "Удаляет команду и ничего не возвращает"
    )
    @DeleteMapping("/{id}/delete")
    ResponseEntity<Void> deleteTeam(
            @PathVariable ("id") int id);

    @Operation(
            summary = "Редактирование команды",
            description = "Обновляет команду"
    )
    @PutMapping("/{id}")
    ResponseEntity<TeamDto> deleteContestant(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable("id") int id,
            @RequestBody TeamDto teamDto);

    @Operation(
            summary = "Редактирование команды",
            description = "Обновляет команду"
    )
    @PutMapping("/{id}/contestant")
    ResponseEntity<ContestantDto> getOutFromTeam(
            @PathVariable("id") int id,
            @RequestBody ContestantDto contestantDto);
}
