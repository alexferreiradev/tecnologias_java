package dev.alexferreira.sampleapi.common.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexferreira.sampleapi.common.container.CustomKafkaContainer;
import dev.alexferreira.sampleapi.common.container.DynamicPropertyConfigurableContainer;
import dev.alexferreira.sampleapi.configuration.LoggerBeanFactory;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducer;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducerMessage;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.test.context.ActiveProfiles;
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
      @Autowired protected KafkaAdmin kafkaAdmin;

      private KafkaConsumer<String, String> kafkaConsumer;
      private TopicPartition partition;
      private boolean started = false;
      protected static final long DEFAULT_TIMEOUT = 30000L;

      protected void startKafka() {
            if (!started) {
                  partition = new TopicPartition(getTestTopicName(), 0);
                  kafkaAdmin.createOrModifyTopics(new NewTopic(getTestTopicName(), 1, Short.parseShort("1")));

                  Map<String, Object> properties = kafkaProperties.buildConsumerProperties();
                  properties.put("auto.offset.reset", "earliest");
                  properties.put("heartbeat.interval.ms", "60000");
                  properties.put("session.timeout.ms", "90000");
                  kafkaConsumer = new KafkaConsumer<>(properties);
                  kafkaConsumer.assign(Collections.singletonList(partition));
                  startKafkaClient();
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

      private void startKafkaClient() {
            kafkaConsumer.seekToEnd(kafkaConsumer.assignment());
            kafkaConsumer.poll(Duration.ofSeconds(15));
      }

      @DynamicPropertySource
      static void datasourceProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
         kafkaContainer.configure(dynamicPropertyRegistry);
      }

}
