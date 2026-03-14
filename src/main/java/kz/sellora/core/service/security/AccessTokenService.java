package kz.sellora.core.service.security;

import kz.sellora.core.model.entity.redis.AccessToken;
import kz.sellora.core.repository.redis.RedisAccessTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccessTokenService {

    private final RedisAccessTokenRepository redisAccessTokenRepository;

    public void saveToken(String username, String token, long expirationMillis) {
        long expirationSeconds = expirationMillis / 1000;

        AccessToken accessTokenEntity = AccessToken.builder()
                .token(token)
                .username(username)
                .expirationSeconds(expirationSeconds)
                .createdAt(Instant.now())
                .build();

        redisAccessTokenRepository.save(accessTokenEntity);
    }

    public boolean exists(String token) {
        return redisAccessTokenRepository.existsById(token);
    }
}
