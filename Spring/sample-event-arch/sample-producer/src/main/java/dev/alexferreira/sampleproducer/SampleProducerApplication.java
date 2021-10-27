package dev.alexferreira.sampleproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class SampleProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleProducerApplication.class, args);
	}

}
