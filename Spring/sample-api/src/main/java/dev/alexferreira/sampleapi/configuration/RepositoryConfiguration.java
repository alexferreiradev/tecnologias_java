package dev.alexferreira.sampleapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableJpaRepositories
@EnableMongoRepositories
public class RepositoryConfiguration {
}
