package ru.vsu.cs.sakovea.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.sakovea.api.AuthApi;
import ru.vsu.cs.sakovea.api.dto.registration.ChangePasswordByEmail;
import ru.vsu.cs.sakovea.api.dto.registration.JWTRequestDto;
import ru.vsu.cs.sakovea.api.dto.registration.JWTResponseDto;
import ru.vsu.cs.sakovea.api.dto.registration.RegistrationDto;
import ru.vsu.cs.sakovea.api.dto.user.ChangePasswordDto;
import ru.vsu.cs.sakovea.api.dto.user.UserDto;
import ru.vsu.cs.sakovea.service.AuthService;
import ru.vsu.cs.sakovea.service.UserService;

@RestController
@SecurityRequirements
public class AuthController implements AuthApi {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }


    @Override
    public ResponseEntity<?> register(@Valid RegistrationDto registrationDto) {
        return ResponseEntity.ok(authService.register(registrationDto));
    }

    @Override
    public ResponseEntity<?> confirmEmail(@Valid String token) {
        return ResponseEntity.ok(authService.confirmEmail(token));
    }

    @Override
    public ResponseEntity<JWTResponseDto> login(@Valid JWTRequestDto jwtRequestDto) {
        return ResponseEntity.ok(authService.login(jwtRequestDto));
    }

    @Override
    public ResponseEntity<?> changePassword(ChangePasswordByEmail changePasswordByEmail) {
        return ResponseEntity.ok(authService.changePassword(changePasswordByEmail));
    }

    @Override
    public ResponseEntity<?> updateUserPassword(ChangePasswordDto changePasswordDto, String token) {
        return ResponseEntity.ok(userService.updateUserPassword(changePasswordDto, token));
    }
}
