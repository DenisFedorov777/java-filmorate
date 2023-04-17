package ru.yandex.practicum.filmorate.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlyWayConfig {

    @Bean
    public FlywayMigrationStrategy initFlyWay(Flyway flyway) {
        return fw -> {
            fw.clean();
            fw.migrate();
        };
    }
}
