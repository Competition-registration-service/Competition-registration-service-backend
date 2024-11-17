package ru.vsu.cs.sakovea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sakovea.api.dto.ChangePasswordDto;
import ru.vsu.cs.sakovea.api.dto.UserDto;
import ru.vsu.cs.sakovea.models.User;
import ru.vsu.cs.sakovea.models.UserCompPerm;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;
import ru.vsu.cs.sakovea.repository.UserCompPermsRepository;
import ru.vsu.cs.sakovea.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserCompPermsRepository userCompPermsRepository;

    private final EmailSenderService emailSenderService;

    public Optional<User> getByUsername(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = getByUsername(login).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", login)
        ));
        System.out.println(user.getLogin());
        UserCompPerm userRole = user.getRoles().getLast();
        System.out.println(userRole.getRefRole().getValueCid());
        return new UserDetailsImpl(user, userRole);
    }

    public void saveUserRole(UserCompPerm userCompPerm) {
        if (userCompPerm != null) {
            userCompPermsRepository.save(userCompPerm);
        }
    }

    public User updateUser(UserDetailsImpl userDetails, UserDto userDto) {
        User existingUser = userDetails.getUser();

        if (existingUser != null) {
            System.out.println(existingUser.getId());
            System.out.println(userDto);
            if (userDto.getBirthDate() != null) {
                existingUser.setBirthDate(userDto.getBirthDate());
            }
            if (userDto.getName() != null) {
                existingUser.setName(userDto.getName());
            }
            if (userDto.getSurname() != null) {
                existingUser.setSurname(userDto.getSurname());
            }
            if (userDto.getThirdname() != null) {
                existingUser.setThirdname(userDto.getThirdname());
            }
            if (userDto.getLogin() != null) {
                existingUser.setLogin(userDto.getLogin());
            }
            if (userDto.getEmail() != null) {
                existingUser.setEmail(userDto.getEmail());
            }
            if (userDto.getTelegram() != null) {
                existingUser.setTelegram(userDto.getTelegram());
            }
            if (userDto.getVk() != null) {
                existingUser.setVk(userDto.getVk());
            }
            if (userDto.getPhone() != null) {
                existingUser.setPhone(userDto.getPhone());
            }
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

//    public void updateUserPassword(UserDetailsImpl userDetails, ChangePasswordDto changePasswordDto) {
//
//    }
}
