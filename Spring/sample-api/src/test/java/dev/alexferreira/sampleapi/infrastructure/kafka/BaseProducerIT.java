package dev.alexferreira.sampleapi.infrastructure.kafka;

import dev.alexferreira.sampleapi.common.test.BaseKafkaIT;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducer;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducerMessage;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseProducerIT extends BaseKafkaIT {

   @Autowired BaseProducer<String> baseProducer;

   @BeforeEach
   void setUp() {
      startKafka();
   }

   @Test
   void shouldIncrementOffset_whenProducerSendMessage() {
      long lastPosition = getLastOffset();
      BaseProducerMessage<String> message = new BaseProducerMessage<>();
      message.topicName = getTestTopicName();
      message.key = "2";
      message.message = "message";
      System.out.println("message = " + message);
      baseProducer.send(message);

      long newPosition = getLastOffset();
      assertEquals(lastPosition + 1, newPosition);
   }
}
