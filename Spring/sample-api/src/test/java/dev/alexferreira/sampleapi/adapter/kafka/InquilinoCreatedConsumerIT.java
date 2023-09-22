package dev.alexferreira.sampleapi.adapter.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.alexferreira.sampleapi.adapter.kafka.message.InquilinoCreatedMessage;
import dev.alexferreira.sampleapi.common.fixture.MessageFixture;
import dev.alexferreira.sampleapi.common.test.BaseKafkaIT;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducerMessage;
import dev.alexferreira.sampleapi.usecase.RegisterUser;
import dev.alexferreira.sampleapi.usecase.input.RegisterInquilinoInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@Import(InquilinoCreatedConsumer.class)
class InquilinoCreatedConsumerIT extends BaseKafkaIT {

   private final InquilinoCreatedMessage message = MessageFixture.inquilinoCreatedMessage();

   @Value("${spring.kafka.producer.properties.topics.inquilino}")
   private String topicName;


   @Test
   void listen() {

   }
}