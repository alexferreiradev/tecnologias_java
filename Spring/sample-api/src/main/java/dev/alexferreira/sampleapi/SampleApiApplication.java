package dev.alexferreira.sampleapi;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.data.jpa.repository.config.*;

@SpringBootApplication(scanBasePackages = "dev.alexferreira.sampleapi")
@EnableJpaRepositories
@EnableAutoConfiguration
public class SampleApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleApiApplication.class, args);
	}

}
