package ru.vsu.cs.sakovea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.vsu.cs.sakovea.api.dto.JWTRequestDto;
import ru.vsu.cs.sakovea.api.dto.JWTResponseDto;
import ru.vsu.cs.sakovea.api.dto.RegistrationDto;
import ru.vsu.cs.sakovea.models.User;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;
import ru.vsu.cs.sakovea.repository.UserRepository;
import ru.vsu.cs.sakovea.security.JwtTokenService;

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

    public ResponseEntity<?> register(RegistrationDto registrationDto) {

        if (registrationDto == null || !isValidRegistrationData(registrationDto)) {
            throw new IllegalArgumentException("Invalid registration data");
        }

        User user = new User();
        user.setName(registrationDto.getName());
        user.setSurname(registrationDto.getSurname());
        user.setThirdname(registrationDto.getThirdname());
        user.setLogin(registrationDto.getLogin());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setBirthDate(registrationDto.getBirthDate());
        user.setGender(registrationDto.getGender());
        user.setPhone(registrationDto.getPhone());


        userRepository.save(user);

        emailSenderService.sendConfirmationEmail(user.getEmail(), user.getActiveCode());

        return ResponseEntity.ok("Регистрация прошла успешно. Подтвердите email.");
    }

    public ResponseEntity<?> confirmEmail(String token) {
        User user = userRepository.findUserByActiveCode(token);
        if (user.getActiveCode() != null) {
            user.setActiveCode(null);
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
        if (user.getActiveCode() == null){
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        jwtRequestDto.getLogin(),
                        jwtRequestDto.getPassword()
                ));

                UserDetailsImpl userDetails = userService.loadUserByUsername(jwtRequestDto.getLogin());
                log.info("User authenticated: {}", userDetails.getUsername());

                String jwt = jwtTokenService.generateToken(userDetails);
                log.debug("JWT token generated: {}", jwt);

                return new JWTResponseDto(jwt);
            } catch (Exception e) {
                log.error("Error during user authentication: {}", e.getMessage(), e);
                throw new RuntimeException("Error during user authentication", e);
            }
        }
        return null;
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
}
