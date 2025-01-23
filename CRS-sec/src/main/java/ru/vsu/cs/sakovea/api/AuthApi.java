package ru.vsu.cs.sakovea.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sakovea.api.dto.registration.JWTRequestDto;
import ru.vsu.cs.sakovea.api.dto.registration.JWTResponseDto;
import ru.vsu.cs.sakovea.api.dto.registration.RegistrationDto;

@RequestMapping("/auth")
@Tag(name = "Контроллер админ-панели", description = "Управление пользователями и соревнованиями")
public interface AuthApi {

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody RegistrationDto registrationDto);

    @Operation(summary = "Подтверждение email")
    @GetMapping("/confirm")
    ResponseEntity<?> confirmEmail(@RequestParam("token") String token);

    @Operation(summary = "Аутентификация пользователя")
    @PostMapping("/login")
    ResponseEntity<JWTResponseDto> login(@RequestBody JWTRequestDto jwtRequestDto);
}
