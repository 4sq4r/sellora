package kz.sellora.core.model.entity.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("refresh_token")
public class RefreshToken {

    @Id
    private String token;

    private String username;

    private String deviceId;

    @TimeToLive
    private Long expirationSeconds;

    private Instant createdAt;
}
