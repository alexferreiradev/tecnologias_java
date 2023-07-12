package dev.alexferreira.sampleapi.common.container;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

public class CustomKafkaContainer extends KafkaContainer
   implements InitializerConfigurableContainer, DynamicPropertyConfigurableContainer {

   public CustomKafkaContainer() {
      super(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"));

      start();
   }

   @Override
   public void configure(ConfigurableApplicationContext applicationContext) {
      TestPropertyValues.of(Map.of("spring.kafka.bootstrap-servers", getBootstrapServers())).applyTo(applicationContext);
   }

   @Override
   public void configure(DynamicPropertyRegistry registry) {
      registry.add("spring.kafka.bootstrap-servers", this::getBootstrapServers);
   }
}
