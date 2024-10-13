package ru.vsu.cs.sakovea.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.sakovea.api.AuthApi;
import ru.vsu.cs.sakovea.api.dto.JWTRequestDto;
import ru.vsu.cs.sakovea.api.dto.JWTResponseDto;
import ru.vsu.cs.sakovea.api.dto.RegistrationDto;
import ru.vsu.cs.sakovea.service.AuthService;

@RestController
@SecurityRequirements
public class AuthController implements AuthApi {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<?> register(RegistrationDto registrationDto) {
        return ResponseEntity.ok(authService.register(registrationDto));
    }

    @Override
    public ResponseEntity<?> confirmEmail(String token) {
        return ResponseEntity.ok(authService.confirmEmail(token));
    }

    @Override
    public ResponseEntity<JWTResponseDto> login(JWTRequestDto jwtRequestDto) {
        return ResponseEntity.ok(authService.login(jwtRequestDto));
    }
}
