package kz.sellora.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(basePackages = "kz.sellora.core.repository.redis")
public class RedisConfiguration {
}
