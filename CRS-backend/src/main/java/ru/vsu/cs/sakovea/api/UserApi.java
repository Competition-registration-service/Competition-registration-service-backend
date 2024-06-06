package ru.vsu.cs.sakovea.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import ru.vsu.cs.sakovea.api.dto.UserDto;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;

@Tag(name = "Контроллер пользователя", description = "Действия с профилем")
@RequestMapping("/user")
public interface UserApi {
    @Operation(
            summary = "Получение пользователя",
            description = "Возвращает информацию о пользователе"
    )
    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUser(@PathVariable("id") Integer id);

    @Operation(
            summary = "Получение пользователя",
            description = "Возвращает информацию о пользователе"
    )
    @GetMapping
    ResponseEntity<UserDto> getUser(@AuthenticationPrincipal UserDetailsImpl userDetails);

    @Operation(
            summary = "Обновление пользователя",
            description = "Обновляет пользователя и возвращает его"
    )
    @PutMapping
    ResponseEntity<UserDto> updateUser(HttpServletResponse response, @RequestBody UserDto userDto);
}
