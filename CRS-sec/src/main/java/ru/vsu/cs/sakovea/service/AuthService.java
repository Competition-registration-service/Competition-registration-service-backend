package ru.vsu.cs.sakovea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.vsu.cs.sakovea.api.dto.registration.ChangePasswordByEmail;
import ru.vsu.cs.sakovea.api.dto.registration.JWTRequestDto;
import ru.vsu.cs.sakovea.api.dto.registration.JWTResponseDto;
import ru.vsu.cs.sakovea.api.dto.registration.RegistrationDto;
import ru.vsu.cs.sakovea.exeptions.CustomException;
import ru.vsu.cs.sakovea.models.User;
import ru.vsu.cs.sakovea.models.UserCompPerm;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;
import ru.vsu.cs.sakovea.models.enums.Role;
import ru.vsu.cs.sakovea.repository.RefValueRepository;
import ru.vsu.cs.sakovea.repository.UserCompPermsRepository;
import ru.vsu.cs.sakovea.repository.UserRepository;
import ru.vsu.cs.sakovea.security.JwtTokenService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final RefValueRepository refValueRepository;
    private final UserCompPermsRepository compPermsRepository;

    public ResponseEntity<?> register(RegistrationDto registrationDto) {

        if (registrationDto == null || !isValidRegistrationData(registrationDto)) {
            throw new CustomException("Invalid registration data");
        }

        if (!isLoginUnique(registrationDto.getLogin())) {
            throw new CustomException("Логин уже занят");
        }

        if (!isEmailUnique(registrationDto.getEmail())) {
            throw new CustomException("Почта уже занята");
        }


        User user = new User();
        user.setName(registrationDto.getName());
        user.setSurname(registrationDto.getSurname());
        user.setThirdname(registrationDto.getThirdname());
        user.setLogin(registrationDto.getLogin());
        user.setEmail(registrationDto.getEmail());
        System.out.println(user.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setBirthDate(registrationDto.getBirthDate());
        user.setGender(registrationDto.getGender());
        user.setPhone(registrationDto.getPhone());

        user.setActiveCode(UUID.randomUUID().toString());

        userRepository.save(user);

        emailSenderService.sendConfirmationEmail(user.getEmail(), user.getActiveCode());

        return ResponseEntity.ok("Регистрация прошла успешно. Подтвердите email.");
    }

    public ResponseEntity<?> confirmEmail(String token) {
        User user = userRepository.findUserByActiveCode(token);
        if (user.getActiveCode() != null) {
            user.setActiveCode(null);
            List<UserCompPerm> roles = new ArrayList<>();
            UserCompPerm userCompPerm = new UserCompPerm();

            userCompPerm.setUser(user);
            userCompPerm.setRefRole(refValueRepository.findRefValueByValueCid(Role.USER.toString()));
            compPermsRepository.save(userCompPerm);
            roles.add(userCompPerm);

            user.setRoles(roles);
            userRepository.save(user);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }

    public JWTResponseDto login(JWTRequestDto jwtRequestDto) {
        log.info("Invalid login data: {}", jwtRequestDto);
        if (jwtRequestDto == null || !isValidLoginData(jwtRequestDto)) {
            log.warn("Invalid login data: {}", jwtRequestDto);
            throw new IllegalArgumentException("Invalid login data");
        }
        User user = userRepository.findUserByLogin(jwtRequestDto.getLogin());
        System.out.println(jwtRequestDto.getLogin());
        System.out.println(user.getLogin());
        if (user.getActiveCode() == null){
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        jwtRequestDto.getLogin(),
                        jwtRequestDto.getPassword()
                ));


                UserDetailsImpl userDetails = userService.loadUserByUsername(jwtRequestDto.getLogin());
                for (UserCompPerm r : userDetails.getUser().getRoles()){
                    System.out.println(r.getRefRole().getValueCid());
                }
                System.out.println("Последняя роль = " + userDetails.getUser().getRoles().getFirst().getRefRole().getValueCid());
                log.info("User authenticated: {}", userDetails.getUsername());

                String jwt = jwtTokenService.generateToken(userDetails);
                log.debug("JWT token generated: {}", jwt);

                return new JWTResponseDto(jwt);
            } catch (Exception e) {
                log.error("Error during user authentication: {}", e.getMessage(), e);
                throw new RuntimeException("Error during user authentication", e);
            }
        }
        throw new CustomException("Пользователь не подтвердил почту");
    }

    public ResponseEntity<?> changePassword(ChangePasswordByEmail email) {
        User user = userRepository.findUserByEmail(email.getEmail());

        user.setActiveCode(UUID.randomUUID().toString());

        userRepository.save(user);

        emailSenderService.sendConfirmationEmail(user.getEmail(), user.getActiveCode());
        return ResponseEntity.ok("Письмо с подтверждением отправлено на почту.");
    }

    private boolean isValidRegistrationData(RegistrationDto request) {
        return StringUtils.hasText(request.getName())
                && StringUtils.hasText(request.getSurname())
                && StringUtils.hasText(request.getLogin())
                && StringUtils.hasText(request.getPassword());
    }

    private boolean isValidLoginData(JWTRequestDto request) {
        return StringUtils.hasText(request.getLogin())
                && StringUtils.hasText(request.getPassword());
    }

    public boolean isLoginUnique(String login) {
        return userRepository.findByLogin(login).isEmpty();
    }

    public boolean isEmailUnique(String email) {
        return userRepository.findUserByEmail(email) == null;
    }
}
