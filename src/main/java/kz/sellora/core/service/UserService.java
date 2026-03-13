package kz.sellora.core.service;

import kz.sellora.core.model.entity.User;
import kz.sellora.core.repository.jpa.UserRepository;
import kz.sellora.core.util.ErrorMessageSource;
import kz.sellora.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void saveOne(String username, String password) {
        if (repository.existsByUsername(username)) {
            throw new CustomException(
                HttpStatus.BAD_REQUEST,
                ErrorMessageSource.USERNAME_ALREADY_EXISTS.getText(username));
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        repository.save(user);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username)
            .orElseThrow(
                () -> new CustomException(
                    HttpStatus.BAD_REQUEST,
                    ErrorMessageSource.INVALID_USERNAME_OR_PASSWORD.getText(username)
                )
            );
    }
}
