package dev.alexferreira.sampleapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "dev.alexferreira.sampleapi")
public class MongoConfiguration { }
