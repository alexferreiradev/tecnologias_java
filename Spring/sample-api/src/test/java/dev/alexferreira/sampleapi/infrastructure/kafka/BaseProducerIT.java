package dev.alexferreira.sampleapi.infrastructure.kafka;

import dev.alexferreira.sampleapi.common.test.BaseKafkaIT;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducer;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducerMessage;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Duration;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseProducerIT extends BaseKafkaIT {

   @Autowired BaseProducer<String> baseProducer;

   @Autowired KafkaTemplate<String, String> kafkaTemplate;

   @Autowired KafkaProperties kafkaProperties;

   @Test
   void send() {
      KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(kafkaProperties.buildConsumerProperties());
      kafkaConsumer.subscribe(Collections.singletonList("topic"));

      BaseProducerMessage<String> message = new BaseProducerMessage<>();
      message.topicName = "topic";
      message.key = "1";
      message.message = "message";
      baseProducer.send(message);
      kafkaTemplate.flush();

      kafkaConsumer.seekToBeginning(kafkaConsumer.assignment());
      ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(5));
      assertEquals(1, records.count());
   }
}
