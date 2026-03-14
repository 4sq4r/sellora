package kz.sellora.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
public class TestContainerConfiguration {

    private static final String TEST = "test";
    private static final String POSTGRES_IMAGE = "postgres:17";
    @Bean
    @ServiceConnection
    static PostgreSQLContainer<?> postgresSQLContainer() {
        return new PostgreSQLContainer<>(POSTGRES_IMAGE)
            .withDatabaseName(TEST)
            .withUsername(TEST)
            .withPassword(TEST);
    }
}
