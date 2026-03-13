package kz.sellora.core.service;

import kz.sellora.core.model.entity.User;
import kz.sellora.core.repository.UserRepository;
import kz.sellora.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthService(userRepository, jwtService, passwordEncoder);
    }

    @Test
    void signUp_ShouldCreateNewUser_WhenUsernameDoesNotExist() {
        String username = "newuser";
        String password = "password123";

        when(userRepository.existsByUsername(username)).thenReturn(false);
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");
        when(jwtService.generate(username)).thenReturn("jwtToken");

        String token = authService.signUp(username, password);

        assertNotNull(token);
        assertEquals("jwtToken", token);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void signUp_ShouldThrowException_WhenUsernameAlreadyExists() {
        String username = "existinguser";
        String password = "password123";

        when(userRepository.existsByUsername(username)).thenReturn(true);

        CustomException exception = assertThrows(
            CustomException.class,
            () -> authService.signUp(username, password)
        );

        assertEquals(400, exception.getHttpStatus().value());
        verify(userRepository, never()).save(any());
    }

    @Test
    void signIn_ShouldReturnToken_WhenCredentialsAreValid() {
        String username = "testuser";
        String password = "password123";
        User user = new User();
        user.setUsername(username);
        user.setPassword("encodedPassword");

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(true);
        when(jwtService.generate(username)).thenReturn("jwtToken");

        String token = authService.signIn(username, password);

        assertNotNull(token);
        assertEquals("jwtToken", token);
    }

    @Test
    void signIn_ShouldThrowException_WhenUserNotFound() {
        String username = "nonexistent";
        String password = "password123";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        CustomException exception = assertThrows(
            CustomException.class,
            () -> authService.signIn(username, password)
        );

        assertEquals(401, exception.getHttpStatus().value());
        assertEquals("Invalid username or password", exception.getMessage());
    }

    @Test
    void signIn_ShouldThrowException_WhenPasswordIsWrong() {
        String username = "testuser";
        String correctPassword = "correctPassword";
        String wrongPassword = "wrongPassword";
        User user = new User();
        user.setUsername(username);
        user.setPassword("encodedPassword");

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(wrongPassword, user.getPassword())).thenReturn(false);

        CustomException exception = assertThrows(
            CustomException.class,
            () -> authService.signIn(username, wrongPassword)
        );

        assertEquals(401, exception.getHttpStatus().value());
        assertEquals("Invalid username or password", exception.getMessage());
    }
}
