package ru.vsu.cs.sakovea.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sakovea.api.dto.registration.ChangePasswordByEmail;
import ru.vsu.cs.sakovea.api.dto.registration.JWTRequestDto;
import ru.vsu.cs.sakovea.api.dto.registration.JWTResponseDto;
import ru.vsu.cs.sakovea.api.dto.registration.RegistrationDto;
import ru.vsu.cs.sakovea.api.dto.user.ChangePasswordDto;
import ru.vsu.cs.sakovea.api.dto.user.UserDto;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;

@RequestMapping("/auth")
@Tag(name = "Контроллер регистрации", description = "Управление пользователями и соревнованиями")
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

    @Operation(
            summary = "Отправка на почту ссылки для подтверждения почты и изменения пароля",
            description = "Отправляет на почту ссылку для подтверждения почты и изменения пароля"
    )
    @PostMapping
    ResponseEntity<?> changePassword(ChangePasswordByEmail changePasswordByEmail);

    @Operation(
            summary = "Обновление пароля пользователя",
            description = "Обновляет пароль пользователя"
    )
    @PutMapping("/update-password")
    ResponseEntity<?> updateUserPassword(@RequestBody ChangePasswordDto changePasswordDto, @RequestParam("token") String token);
}
