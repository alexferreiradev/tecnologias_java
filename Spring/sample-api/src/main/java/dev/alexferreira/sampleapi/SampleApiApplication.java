package dev.alexferreira.sampleapi;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.data.jpa.repository.config.*;
import org.springframework.data.mongodb.repository.config.*;

@SpringBootApplication(scanBasePackages = "dev.alexferreira.sampleapi")
@EnableJpaRepositories
@EnableMongoRepositories
@EnableAutoConfiguration
public class SampleApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleApiApplication.class, args);
	}

}
