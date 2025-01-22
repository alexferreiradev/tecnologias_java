package dev.alexferreira.sampleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class GateApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateApiApplication.class, args);
	}

}
