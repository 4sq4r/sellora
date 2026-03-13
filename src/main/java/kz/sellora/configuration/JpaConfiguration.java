package kz.sellora.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "kz.sellora.core.repository.jpa")
public class JpaConfiguration {
}
