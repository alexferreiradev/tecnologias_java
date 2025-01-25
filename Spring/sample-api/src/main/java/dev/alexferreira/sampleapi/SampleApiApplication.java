package dev.alexferreira.sampleapi;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableJpaRepositories
@EnableMongoRepositories
public class SampleApiApplication {

	public static void main(String[] args) {
	}

}
