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
import ru.vsu.cs.sakovea.exeptions.CustomException;
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
        try {
            return ResponseEntity.ok(authService.register(registrationDto));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }
    }

    @Override
    public ResponseEntity<?> confirmEmail(@Valid String token) {
        try {
            return ResponseEntity.ok(authService.confirmEmail(token));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }

    }

    @Override
    public ResponseEntity<?> login(@Valid JWTRequestDto jwtRequestDto) {
        try {
            return ResponseEntity.ok(authService.login(jwtRequestDto));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }

    }

    @Override
    public ResponseEntity<?> changePassword(ChangePasswordByEmail changePasswordByEmail) {
        try {
            return ResponseEntity.ok(authService.changePassword(changePasswordByEmail));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }

    }

    @Override
    public ResponseEntity<?> updateUserPassword(ChangePasswordDto changePasswordDto) {
        try {
            return ResponseEntity.ok(userService.updateUserPassword(changePasswordDto));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessageAsJson());
        }
    }
}
