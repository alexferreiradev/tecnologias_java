package dev.alexferreira.sampleapi.common.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexferreira.sampleapi.common.container.CustomKafkaContainer;
import dev.alexferreira.sampleapi.common.container.DynamicPropertyConfigurableContainer;
import dev.alexferreira.sampleapi.configuration.LoggerBeanFactory;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducer;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducerMessage;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

@ActiveProfiles("dev")
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-dev.properties")
@Import({
   KafkaAutoConfiguration.class,
   JacksonAutoConfiguration.class,
   LoggerBeanFactory.class,
   BaseProducer.class,
   BaseProducerMessage.class,
})
public abstract class BaseKafkaIT {

      private static final DynamicPropertyConfigurableContainer kafkaContainer = new CustomKafkaContainer();

      @Autowired protected BaseProducer<String> baseProducer;
      @Autowired protected ObjectMapper objectMapper;
      @Autowired protected KafkaProperties kafkaProperties;

      private KafkaConsumer<String, String> kafkaConsumer;
      private final TopicPartition partition;
      private boolean started = false;

      protected BaseKafkaIT() {
            partition = new TopicPartition(getTestTopicName(), 0);
      }

      protected void startKafka() {
            if (!started) {
                  Map<String, Object> properties = kafkaProperties.buildConsumerProperties();
                  properties.put("consumer.auto-offset-reset", "lasted");
                  kafkaConsumer = new KafkaConsumer<>(properties);
                  kafkaConsumer.subscribe(Collections.singletonList(getTestTopicName()));
                  startKafkaClient(kafkaConsumer);
                  started = true;
            }
      }

      protected String getTestTopicName() {
         return "test-topic";
      }

      protected long getLastOffset() {
            kafkaConsumer.poll(Duration.ofSeconds(10));
            return kafkaConsumer.position(partition);
      }

      private void startKafkaClient(KafkaConsumer<String, String> kafkaConsumer) {
            kafkaConsumer.seekToEnd(kafkaConsumer.assignment());
            kafkaConsumer.poll(Duration.ofSeconds(15));
      }

      protected static final long DEFAULT_TIMEOUT = 30000L;

      @DynamicPropertySource
      static void datasourceProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
         kafkaContainer.configure(dynamicPropertyRegistry);
      }

}
