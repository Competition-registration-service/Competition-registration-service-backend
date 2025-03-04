package ru.vsu.cs.sakovea.api.controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.sakovea.api.UserApi;
import ru.vsu.cs.sakovea.api.dto.user.ChangePasswordDto;
import ru.vsu.cs.sakovea.api.dto.user.GetUserDto;
import ru.vsu.cs.sakovea.api.dto.user.UserDto;
import ru.vsu.cs.sakovea.models.User;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;
import ru.vsu.cs.sakovea.repository.UserRepository;
import ru.vsu.cs.sakovea.service.AuthService;
import ru.vsu.cs.sakovea.service.UserService;


@RestController
@AllArgsConstructor
public class UserController implements UserApi {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

//    @Override
//    public ResponseEntity<UserDto> getUser(Integer id) {
//        return null;
//    }

    @Override
    public ResponseEntity<GetUserDto> getUser(UserDetailsImpl userDetails) {
        return ResponseEntity.ok(userService.getUser(userDetails));
    }

    @Override
    public ResponseEntity<UserDto> updateUser(HttpServletResponse response, UserDetailsImpl userDetails, @Valid UserDto userDto) {
        userService.updateUser(userDetails, userDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> updateUserPassword(ChangePasswordDto changePasswordDto) {
        return ResponseEntity.ok(userService.updateUserPassword(changePasswordDto));
    }

    @Override
    public ResponseEntity<?> changePassword(UserDetailsImpl userDetails) {
        return ResponseEntity.ok(userService.changePassword(userDetails));
    }

}
