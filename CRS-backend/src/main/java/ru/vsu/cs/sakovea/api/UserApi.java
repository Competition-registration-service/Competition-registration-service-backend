package ru.vsu.cs.sakovea.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import ru.vsu.cs.sakovea.api.dto.CompetitionDto;
import ru.vsu.cs.sakovea.api.dto.ContestantDto;
import ru.vsu.cs.sakovea.api.dto.UserDto;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;

import java.util.List;

@Tag(name = "Контроллер пользователя", description = "Действия с профилем")
@RequestMapping("/user")
public interface UserApi {
    @Operation(
            summary = "Получение пользователя",
            description = "Возвращает информацию о пользователе"
    )
    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUser(
            @PathVariable("id") Integer id);

    @Operation(
            summary = "Обновление пользователя",
            description = "Обновляет пользователя и возвращает его"
    )
    @PutMapping
    ResponseEntity<UserDto> updateUser(
            HttpServletResponse response,
            @RequestBody UserDto userDto);

    @Operation(
            summary = "Получение списка соревнований",
            description = "Возвращает список соревнований"
    )
    @GetMapping("/competitions")
    ResponseEntity<List<CompetitionDto>> getUserCompetition();

    @Operation(
            summary = "Получение информации о конкретном соревнование пользователя",
            description = "Возвращает информацию о конкретном соревнование пользователя"
    )
    @GetMapping("/competition/{id}/contestant")
    ResponseEntity<ContestantDto> getUserCompetitionInfo(
            @PathVariable("id") Integer id);

    @Operation(
            summary = "Действия с паролем (восстановление и смена)",
            description = "Восстонавливает или меняет пароль"
    )
    @PutMapping
    ResponseEntity<UserDto> updateUserPassword(
            HttpServletResponse response,
            @RequestBody UserDto userDto);
}
