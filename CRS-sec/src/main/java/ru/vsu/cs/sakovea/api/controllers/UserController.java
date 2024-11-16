package ru.vsu.cs.sakovea.api.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.sakovea.api.UserApi;
import ru.vsu.cs.sakovea.api.dto.ChangePasswordDto;
import ru.vsu.cs.sakovea.api.dto.UserDto;
import ru.vsu.cs.sakovea.models.User;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;
import ru.vsu.cs.sakovea.repository.UserRepository;
import ru.vsu.cs.sakovea.service.UserService;


@RestController
@AllArgsConstructor
public class UserController implements UserApi {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

//    @Override
//    public ResponseEntity<UserDto> getUser(Integer id) {
//        return null;
//    }

    @Override
    public ResponseEntity<UserDto> getUser(UserDetailsImpl userDetails) {
        UserDto profileDto = new UserDto();

        var user = userRepository.findById(userDetails.getUser().getId()).orElseThrow();
        profileDto.setId(user.getId());
        profileDto.setLogin(user.getLogin());
        profileDto.setName(user.getName());
        profileDto.setSurname(user.getSurname());
        profileDto.setThirdname(user.getThirdname());
        profileDto.setBirthDate(user.getBirthDate());
        profileDto.setEmail(user.getEmail());
        profileDto.setTelegram(user.getTelegram());
        profileDto.setVk(user.getVk());

        return ResponseEntity.ok(profileDto);
    }

    @Override
    public ResponseEntity<UserDto> updateUser(HttpServletResponse response, UserDetailsImpl userDetails, UserDto userDto) {
        userService.updateUser(userDetails, userDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UserDto> updateUserPassword(HttpServletResponse response, UserDetailsImpl userDetails,
                                                      UserDto changePasswordDto) {
        User existingUser = userDetails.getUser();

        if (existingUser != null) {
            System.out.println(existingUser.getId());
            System.out.println(changePasswordDto);

            if (changePasswordDto.getPassword() != null) {
                existingUser.setPassword(passwordEncoder.encode(changePasswordDto.getPassword()));
                System.out.println(existingUser.getPassword());
            }
            userRepository.save(existingUser);
        }
//        userService.updateUserPassword(userDetails, changePasswordDto);
        return ResponseEntity.noContent().build();
    }
}
