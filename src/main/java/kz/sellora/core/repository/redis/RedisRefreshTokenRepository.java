package kz.sellora.core.repository.redis;

import kz.sellora.core.model.entity.redis.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    void delete(RefreshToken refreshToken);

    void deleteById(String token);

    boolean existsById(String token);
}
