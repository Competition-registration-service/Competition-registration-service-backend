package ru.vsu.cs.sakovea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.vsu.cs.sakovea.api.dto.JWTRequestDto;
import ru.vsu.cs.sakovea.api.dto.JWTResponseDto;
import ru.vsu.cs.sakovea.api.dto.RegistrationDto;
import ru.vsu.cs.sakovea.models.User;
import ru.vsu.cs.sakovea.repository.UserRepository;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String register(RegistrationDto registrationDto) {

        if (registrationDto == null || !isValidRegistrationData(registrationDto)) {
            throw new IllegalArgumentException("Invalid registration data");
        }

        User user = new User();
        user.setName(registrationDto.getName());
        user.setSurename(registrationDto.getSurename());
        user.setThirdname(registrationDto.getThirdname());
        user.setLogin(registrationDto.getLogin());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setBirthDate(registrationDto.getBirthDate());
        user.setGender(registrationDto.getGender());
        user.setPhone(registrationDto.getPhone());


        userRepository.save(user);

        emailSenderService.sendConfirmationEmail(user.getEmail(), user.getActiveCode());

        return "Регистрация прошла успешно. Подтвердите email.";
    }

    public String confirmEmail(String token) {
        return null;
    }

    public JWTResponseDto login(JWTRequestDto jwtRequestDto) {
        return null;
    }

    private boolean isValidRegistrationData(RegistrationDto request) {
        return StringUtils.hasText(request.getName())
                && StringUtils.hasText(request.getSurename())
                && StringUtils.hasText(request.getLogin())
                && StringUtils.hasText(request.getPassword());
    }

    private boolean isValidLoginData(JWTRequestDto request) {
        return StringUtils.hasText(request.getLogin())
                && StringUtils.hasText(request.getPassword());
    }
}
