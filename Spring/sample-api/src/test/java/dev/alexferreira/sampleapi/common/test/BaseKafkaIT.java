package dev.alexferreira.sampleapi.common.test;

import dev.alexferreira.sampleapi.common.container.CustomKafkaContainer;
import dev.alexferreira.sampleapi.common.container.DynamicPropertyConfigurableContainer;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducer;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducerMessage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("dev")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
   KafkaAutoConfiguration.class,
})
@EnableKafka
@TestPropertySource(locations = "classpath:application-dev.properties")
@Import({JacksonAutoConfiguration.class, BaseProducer.class, BaseProducerMessage.class})
public class BaseKafkaIT {

      private static final DynamicPropertyConfigurableContainer kafkaContainer = new CustomKafkaContainer();

      @DynamicPropertySource
      static void datasourceProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
         kafkaContainer.configure(dynamicPropertyRegistry);
      }

}
