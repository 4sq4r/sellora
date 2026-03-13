package kz.sellora.core.repository.redis;

import kz.sellora.core.model.entity.redis.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisAccessTokenRepository extends JpaRepository<AccessToken, String> {

    boolean existsById(String token);
}
