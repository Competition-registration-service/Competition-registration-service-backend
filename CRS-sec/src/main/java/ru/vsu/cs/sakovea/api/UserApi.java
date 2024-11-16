package ru.vsu.cs.sakovea.api;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sakovea.api.dto.ChangePasswordDto;
import ru.vsu.cs.sakovea.api.dto.UserDto;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;

@RequestMapping("/user")
public interface UserApi {

//    @Operation(
//            summary = "Получение пользователя",
//            description = "Возвращает информацию о пользователе"
//    )
//    @GetMapping("/{id}")
//    ResponseEntity<UserDto> getUser(@PathVariable("id") Integer id);

    @Operation(
            summary = "Получение пользователя",
            description = "Возвращает информацию о пользователе"
    )
    @GetMapping("/profile")
    ResponseEntity<UserDto> getUser(@AuthenticationPrincipal UserDetailsImpl userDetails);

    @Operation(
            summary = "Обновление пользователя",
            description = "Обновляет пользователя и возвращает его"
    )
    @PutMapping("/profile/update")
    ResponseEntity<UserDto> updateUser(HttpServletResponse response,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails,
                                       @RequestBody UserDto userDto);

    @Operation(
            summary = "Обновление пароля пользователя",
            description = "Обновляет пароль пользователя"
    )
    @PutMapping("/profile/update-password")
    ResponseEntity<UserDto> updateUserPassword(HttpServletResponse response,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails,
                                       @RequestBody UserDto changePasswordDto);
}
