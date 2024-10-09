package ru.vsu.cs.sakovea.api;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sakovea.api.dto.JWTRequestDto;
import ru.vsu.cs.sakovea.api.dto.JWTResponseDto;
import ru.vsu.cs.sakovea.api.dto.RegistrationDto;

@RequestMapping("/api/auth")
public interface AuthApi {

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody RegistrationDto registrationDto);

    @Operation(summary = "Подтверждение email")
    @GetMapping("/confirm")
    ResponseEntity<String> confirmEmail(@RequestParam("token") String token);

    @Operation(summary = "Аутентификация пользователя")
    @PostMapping("/login")
    ResponseEntity<JWTResponseDto> login(@RequestBody JWTRequestDto jwtRequestDto);
}
