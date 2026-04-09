package kz.sellora.core.service.security;

import kz.sellora.configuration.security.JwtProperties;
import kz.sellora.core.model.entity.redis.RefreshToken;
import kz.sellora.core.repository.redis.RedisRefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RedisRefreshTokenRepository redisRefreshTokenRepository;
//    private final JwtService jwtService;
    private final JwtProperties jwtProperties;

    public String createRefreshToken(String username, String deviceId) {
        if (deviceId == null || deviceId.isBlank()) {
            deviceId = UUID.randomUUID().toString();
        }

//        String token = jwtService.generateRefreshToken(username, deviceId);

        long expirationSeconds = jwtProperties.getRefreshTokenExpiration() / 1000;

        RefreshToken refreshToken = RefreshToken.builder()
//            .token(token)
            .username(username)
            .deviceId(deviceId)
            .expirationSeconds(expirationSeconds)
            .createdAt(Instant.now())
            .build();

        redisRefreshTokenRepository.save(refreshToken);
        log.debug("Refresh token created for user: {}, deviceId: {}", username, deviceId);

//        return token;
        return null;
    }

    public String rotateRefreshToken(String refreshToken, String deviceId) {
//        if (!jwtService.isValidToken(refreshToken) || jwtService.isTokenRefresh(refreshToken)) {
//            log.warn("Invalid refresh token provided");
//            return null;
//        }

        RefreshToken storedToken = redisRefreshTokenRepository.findById(refreshToken).orElse(null);
        if (storedToken == null) {
            log.warn("Refresh token not found in database");
            return null;
        }

        String username = storedToken.getUsername();
        String newDeviceId = (deviceId != null && !deviceId.isBlank()) ? deviceId : storedToken.getDeviceId();

        redisRefreshTokenRepository.deleteById(refreshToken);
        log.debug("Old refresh token deleted for user: {}", username);

        return createRefreshToken(username, newDeviceId);
    }

    public boolean isValidRefreshToken(String refreshToken) {
//        if (!jwtService.isValidToken(refreshToken) || jwtService.isTokenRefresh(refreshToken)) {
//            return false;
//        }
        return redisRefreshTokenRepository.existsById(refreshToken);
    }

    public String getUsernameByRefreshToken(String refreshToken) {
        RefreshToken token = redisRefreshTokenRepository.findById(refreshToken).orElse(null);
        return token != null ? token.getUsername() : null;
    }
}
