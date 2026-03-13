package kz.sellora.core.service;

import kz.sellora.configuration.security.JwtProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        JwtProperties properties = new JwtProperties();
        properties.setSecret("NYyi1S20GJufEk8aKx3eicgpa789tq9N");
        properties.setExpiration(3600000L);
        jwtService = new JwtService(properties);
    }

    @Test
    void generateToken_ShouldReturnValidToken() {
        String username = "testuser";

        String token = jwtService.generate(username);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void extractUsername_ShouldReturnCorrectUsername() {
        String username = "testuser";
        String token = jwtService.generate(username);

        String extractedUsername = jwtService.extractUsername(token);

        assertEquals(username, extractedUsername);
    }

    @Test
    void isValidToken_ShouldReturnTrueForValidToken() {
        String username = "testuser";
        String token = jwtService.generate(username);

        boolean isValid = jwtService.isValidToken(token);

        assertTrue(isValid);
    }

    @Test
    void isValidToken_ShouldReturnFalseForInvalidToken() {
        String invalidToken = "invalid.token.here";

        boolean isValid = jwtService.isValidToken(invalidToken);

        assertFalse(isValid);
    }

    @Test
    void isValidToken_ShouldReturnFalseForEmptyToken() {
        String emptyToken = "";

        assertThrows(Exception.class, () -> jwtService.isValidToken(emptyToken));
    }
}
