package ru.vsu.cs.sakovea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sakovea.models.User;
import ru.vsu.cs.sakovea.models.UserCompPerm;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;
import ru.vsu.cs.sakovea.repository.UserCompPermsRepository;
import ru.vsu.cs.sakovea.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserCompPermsRepository userCompPermsRepository;

    public Optional<User> getByUsername(String login) {
        return userRepository.findByLogin(login);
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
            userCompPermsRepository.save(userCompPerm);
        }
    }
}
