package org.example.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("org.example.models")
@EnableJpaRepositories("org.example.repositories")
@EnableTransactionManagement
public class DatabaseConfiguration {
}
