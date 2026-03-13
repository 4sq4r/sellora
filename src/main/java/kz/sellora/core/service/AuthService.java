package kz.sellora.core.service;

import kz.sellora.core.model.entity.User;
import kz.sellora.core.repository.UserRepository;
import kz.sellora.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public String signUp(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);

        return jwtService.generate(username);
    }

    public String signIn(String username, String password) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new CustomException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        return jwtService.generate(username);
    }
}
