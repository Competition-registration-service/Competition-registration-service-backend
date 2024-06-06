package ru.vsu.cs.sakovea.api.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.sakovea.api.AuthApi;
import ru.vsu.cs.sakovea.api.dto.JWTRegistrationDto;
import ru.vsu.cs.sakovea.api.dto.JWTRequest;

//@RestController
//@RequiredArgsConstructor
//public class AuthController implements AuthApi {
//    private final AuthService authService;
//    @Override
//    public ResponseEntity<String> logout() {
//        System.out.println("logout");
//        return ResponseEntity.ok("Пользователь вышел из системы");
//    }
//
//    @Override
//    public ResponseEntity login(HttpServletResponse response, JWTRequest jwtRequest) {
//        try {
//            var token = authService.signIn(jwtRequest);
//            response.addHeader("Set-Cookie", "token=" + token.getToken() + "; HttpOnly; Secure; SameSite=Strict");
//            return ResponseEntity.noContent().build();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @Override
//    public ResponseEntity register(HttpServletResponse response, JWTRegistrationDto jwtRegistrationDto) {
//        try {
//            var token = authService.signUp(jwtRegistrationDto);
//            response.addHeader("Set-Cookie", "token=" + token.getToken() + "; HttpOnly; Secure; SameSite=Strict");
//            return ResponseEntity.noContent().build();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//}
