package kz.sellora.core.service;

import kz.sellora.core.model.entity.Company;
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

    public User saveOne(String username, String password, Company company) {
        if (repository.existsByUsername(username)) {
            throw CustomException.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(ErrorMessageSource.USERNAME_ALREADY_EXISTS.getText(username))
                .build();
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCompany(company);

        return repository.save(user);
    }

    public User getOne(String id) {
        return repository.findById(id).orElseThrow(
            () -> CustomException.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(ErrorMessageSource.USER_NOT_FOUND.getText(id))
                .build()
        );
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username)
            .orElseThrow(
                () -> CustomException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(ErrorMessageSource.INVALID_USERNAME_OR_PASSWORD.getText(username))
                    .build()
            );
    }
}
