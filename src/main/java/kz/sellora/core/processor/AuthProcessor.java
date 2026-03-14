package kz.sellora.core.processor;

import kz.sellora.configuration.security.JwtProperties;
import kz.sellora.core.model.entity.User;
import kz.sellora.core.service.security.AccessTokenService;
import kz.sellora.core.service.security.JwtService;
import kz.sellora.core.service.security.RefreshTokenService;
import kz.sellora.core.service.UserService;
import kz.sellora.core.util.ErrorMessageSource;
import kz.sellora.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthProcessor {

    private final UserService userService;
    private final JwtService jwtService;
    private final AccessTokenService accessTokenService;
    private final RefreshTokenService refreshTokenService;
    private final JwtProperties jwtProperties;
    private final PasswordEncoder passwordEncoder;

    public AuthTokens signUp(String username, String password, String deviceId) {
        userService.saveOne(username, password);
        AuthTokens authTokens = generateTokens(username, deviceId);
        accessTokenService.saveToken(username, authTokens.accessToken(), jwtProperties.getAccessTokenExpiration());

        return authTokens;
    }

    public AuthTokens signIn(String username, String password, String deviceId) {
        User user = userService.findByUsername(username);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, ErrorMessageSource.INVALID_USERNAME_OR_PASSWORD.getText());
        }

        AuthTokens authTokens = generateTokens(username, deviceId);
        accessTokenService.saveToken(username, authTokens.accessToken(), jwtProperties.getAccessTokenExpiration());

        return authTokens;
    }

    public AuthTokens refresh(String refreshToken, String deviceId) {
        if (!refreshTokenService.isValidRefreshToken(refreshToken)) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
        }

        String newRefreshToken = refreshTokenService.rotateRefreshToken(refreshToken, deviceId);

        if (newRefreshToken == null) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "Failed to rotate refresh token");
        }

        String username = refreshTokenService.getUsernameByRefreshToken(refreshToken);

        if (username == null) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "Refresh token not found");
        }

        String accessToken = jwtService.generateAccessToken(username);
        accessTokenService.saveToken(username, accessToken, jwtProperties.getAccessTokenExpiration());

        return new AuthTokens(accessToken, newRefreshToken);
    }

    public AuthTokens generateTokens(String username, String deviceId) {
        String accessToken = jwtService.generateAccessToken(username);
        String refreshToken = refreshTokenService.createRefreshToken(username, deviceId);

        return new AuthTokens(accessToken, refreshToken);
    }
    public record AuthTokens(String accessToken, String refreshToken) {
    }
}
