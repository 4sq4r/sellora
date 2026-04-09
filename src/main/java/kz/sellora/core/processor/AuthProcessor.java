package kz.sellora.core.processor;

import kz.sellora.configuration.security.JwtProperties;
import kz.sellora.core.model.entity.User;
import kz.sellora.core.service.security.AccessTokenService;
//import kz.sellora.core.service.security.JwtService;
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
//    private final JwtService jwtService;
    private final AccessTokenService accessTokenService;
    private final RefreshTokenService refreshTokenService;
    private final JwtProperties jwtProperties;
    private final PasswordEncoder passwordEncoder;


    public AuthTokens signIn(String username, String password, String deviceId) {
        User user = userService.findByUsername(username);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw CustomException.builder()
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message(ErrorMessageSource.INVALID_USERNAME_OR_PASSWORD.getText())
                .build();
        }

        String companyId = user.getCompany() != null ? user.getCompany().getId().toString() : null;
        String companyName = user.getCompany() != null ? user.getCompany().getName() : null;

        AuthTokens authTokens = generateTokens(username, deviceId, companyId, companyName);
        accessTokenService.saveToken(username, authTokens.accessToken(), jwtProperties.getAccessTokenExpiration());

        return authTokens;
    }

    public AuthTokens refresh(String refreshToken, String deviceId) {
        if (!refreshTokenService.isValidRefreshToken(refreshToken)) {
            throw CustomException.builder()
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message(ErrorMessageSource.INVALID_REFRESH_TOKEN.getText())
                .build();
        }

        String newRefreshToken = refreshTokenService.rotateRefreshToken(refreshToken, deviceId);

        if (newRefreshToken == null) {
            throw CustomException.builder()
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message(ErrorMessageSource.FAILED_TO_ROTATE_REFRESH_TOKEN.getText())
                .build();
        }

        String username = refreshTokenService.getUsernameByRefreshToken(refreshToken);

        if (username == null) {
            throw CustomException.builder()
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message(ErrorMessageSource.REFRESH_TOKEN_NOT_FOUND.getText())
                .build();
        }

//        String accessToken = jwtService.generateAccessToken(username);
//        accessTokenService.saveToken(username, accessToken, jwtProperties.getAccessTokenExpiration());

//        return new AuthTokens(accessToken, newRefreshToken);
        return null;
    }

    public AuthTokens generateTokens(String username, String deviceId) {
        return generateTokens(username, deviceId, null, null);
    }

    public AuthTokens generateTokens(String username, String deviceId, String companyId, String companyName) {
//        String accessToken = jwtService.generateAccessToken(username, companyId, companyName);
        String refreshToken = refreshTokenService.createRefreshToken(username, deviceId);

//        return new AuthTokens(accessToken, refreshToken);
        return null;
    }
    public record AuthTokens(String accessToken, String refreshToken) {
    }
}
