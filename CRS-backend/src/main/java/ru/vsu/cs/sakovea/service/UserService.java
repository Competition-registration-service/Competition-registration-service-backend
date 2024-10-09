package ru.vsu.cs.sakovea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sakovea.models.User;
import ru.vsu.cs.sakovea.models.UserCompPerm;
import ru.vsu.cs.sakovea.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public Optional<User> getByUsername(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        String[] loginSplit = login.split("-");
        String username = loginSplit[0];
        String schoolId = loginSplit.length > 1 ? loginSplit[1] : "null";
        User user = getByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username)
        ));
        UserCompPerm userRole;
        if (!schoolId.isBlank() && !schoolId.equals("null")) {
            // TODO: пока норм, но только если у пользователя не более одной роли в школе
            School school = schoolRepository.findSchoolById(Integer.parseInt(schoolId));
            userRole = userRoleRepository.findUserRoleByUserAndSchool(user, school);
        } else {
            List<UserRole> userRoles = userRoleRepository.findAllByUser(user);
            // TODO: как будто всё равно возвращает не по последнему логину
            userRole = userRoles.isEmpty() ? null : userRoles.stream().sorted(Comparator.comparing(UserRole::getLastLogin)).findFirst().orElse(null);
        }
        return new UserDetailsImpl(user, userRole);
    }
}
