package ru.vsu.cs.sakovea.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.sakovea.api.dto.user.ChangePasswordDto;
import ru.vsu.cs.sakovea.api.dto.user.GetUserDto;
import ru.vsu.cs.sakovea.api.dto.user.UserDto;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;

@RequestMapping("/user")
@Tag(name = "Контроллер Пользователя", description = "Управление пользователем")
public interface UserApi {

    @Operation(
            summary = "Получение пользователя",
            description = "Возвращает информацию о пользователе"
    )
    @GetMapping("/profile")
    ResponseEntity<GetUserDto> getUser(@AuthenticationPrincipal UserDetailsImpl userDetails);

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
    ResponseEntity<?> updateUserPassword(@RequestBody ChangePasswordDto changePasswordDto, @RequestParam("token") String token);

    @Operation(
            summary = "Отправка на почту ссылки для подтверждения почты и изменения пароля",
            description = "Отправляет на почту ссылку для подтверждения почты и изменения пароля"
    )
    @PostMapping("/profile")
    ResponseEntity<?> changePassword(@AuthenticationPrincipal UserDetailsImpl userDetails);

}
