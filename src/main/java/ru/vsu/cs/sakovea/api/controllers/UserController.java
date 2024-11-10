package ru.vsu.cs.sakovea.api.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.sakovea.api.UserApi;
import ru.vsu.cs.sakovea.api.dto.ChangePasswordDto;
import ru.vsu.cs.sakovea.api.dto.UserDto;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;
import ru.vsu.cs.sakovea.repository.UserRepository;
import ru.vsu.cs.sakovea.service.UserService;


@RestController
@AllArgsConstructor
public class UserController implements UserApi {

    private final UserRepository userRepository;
    private final UserService userService;

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
                                                      ChangePasswordDto changePasswordDto) {
        userService.updateUserPassword(userDetails, changePasswordDto);
        return ResponseEntity.noContent().build();
    }
}
