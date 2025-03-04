package ru.vsu.cs.sakovea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sakovea.api.dto.registration.ChangePasswordByEmail;
import ru.vsu.cs.sakovea.api.dto.user.*;
import ru.vsu.cs.sakovea.exeptions.ThrowMyException;
import ru.vsu.cs.sakovea.mapper.UserMapper;
import ru.vsu.cs.sakovea.models.User;
import ru.vsu.cs.sakovea.models.UserCompPerm;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;
import ru.vsu.cs.sakovea.models.enums.Role;
import ru.vsu.cs.sakovea.repository.CompetitionRepository;
import ru.vsu.cs.sakovea.repository.RefValueRepository;
import ru.vsu.cs.sakovea.repository.UserCompPermsRepository;
import ru.vsu.cs.sakovea.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserCompPermsRepository userCompPermsRepository;

    private final EmailSenderService emailSenderService;

    private final RefValueRepository refValueRepository;
    private final CompetitionRepository competitionRepository;

    public Optional<User> getByUsername(String login) {
        return userRepository.findByLogin(login);
    }

    private void checkIsUserAdmin(UserDetailsImpl userDetails) {
        if (Boolean.TRUE.equals(userDetails.getUser().isAdmin()) || (userDetails.getUser().getRoles().getFirst().
                getRefRole().getValueCid().equals(refValueRepository.findRefValueByValueCid("ADMIN").getValueCid()))) {
            return;
        }
        throw new ThrowMyException("Доступ запрещён");
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = getByUsername(login).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", login)
        ));
        UserCompPerm userRole = user.getRoles().getLast();
        return new UserDetailsImpl(user, userRole);
    }

    public void saveUserRole(UserCompPerm userCompPerm) {
        if (userCompPerm != null) {
            userCompPerm.getUser().getRoles().add(userCompPerm);
            userRepository.save(userCompPerm.getUser());
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
        }
        throw new ThrowMyException("Пользователя не существует или нечего изменять");
    }

    public GetUserDto getUser(UserDetailsImpl userDetails) {
        User user = userRepository.findUserByLogin(userDetails.getUser().getLogin());
        if (user != null) {
            return UserMapper.INSTANCE.toGetUserDto(user);
        }
        throw new ThrowMyException("Такого пользователя нет");
    }

    public ResponseEntity<?> updateUserPassword(ChangePasswordDto changePasswordDto) {
        User user = userRepository.findUserByEmail(changePasswordDto.getEmail());
        if (user != null) {
            user.setPassword(changePasswordDto.getPassword());
            userRepository.save(user);
            return ResponseEntity.ok("Пароль успешно изменен");
        }
        throw new ThrowMyException("Подтверждение не получено. Ссылка не действительна или пользователя с такой почтой " +
                "не существует, введите почту на которую регистрировались!");
    }

    public List<GetUserForAdminDto> getAllUsersPagination(UserDetailsImpl userDetails, Integer offset, Integer limit) {
        checkIsUserAdmin(userDetails);
        return UserMapper.INSTANCE.toUserForAdminDtoList(userRepository.findAll(PageRequest.of(offset, limit)).getContent());
    }



    public ResponseEntity<?> changePassword(UserDetailsImpl userDetails) {
        User user = userRepository.findUserByLogin(userDetails.getUser().getLogin());

        user.setActiveCode(UUID.randomUUID().toString());

        userRepository.save(user);

        emailSenderService.sendConfirmationEmail(user.getEmail(), user.getActiveCode());
        return ResponseEntity.ok("Письмо с подтверждением отправлено на почту.");
    }

    public UserDto getUserForAdmin(UserDetailsImpl userDetails, int userId) {
        checkIsUserAdmin(userDetails);
        User user = userRepository.findById(userId);
        if (user != null) {
            return UserMapper.INSTANCE.toUserDto(user);
        }
        throw new ThrowMyException("Такого пользователя нет");
    }

    public UserDto changeUserRole(UserDetailsImpl userDetails, int userId, ChangeUserRoleDto changeUserRoleDto) {
        checkIsUserAdmin(userDetails);
        User user = userRepository.findById(userId);
        UserCompPerm userRole = new UserCompPerm();
        if (user != null) {
            if (user.getRoles().getLast().getCompetition() ==
                    competitionRepository.findById(changeUserRoleDto.getCompetitionId())){
                user.getRoles().getLast().setRefRole(refValueRepository.findRefValueByValueCid(changeUserRoleDto.getNewRole()));
                userRepository.save(user);
                return UserMapper.INSTANCE.toUserDto(user);
            } else {
                userRole.setRefRole(refValueRepository.findRefValueByValueCid(changeUserRoleDto.getNewRole()));
                userRole.setCompetition(competitionRepository.findById(changeUserRoleDto.getCompetitionId()));
                userRole.setUser(user);
                userCompPermsRepository.save(userRole);
                user.getRoles().add(userRole);
                userRepository.save(user);
                return UserMapper.INSTANCE.toUserDto(user);
            }
        }
        throw new ThrowMyException("Такого пользователя нет!");
    }
}
