package dev.alexferreira.sampleapi.infrastructure.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexferreira.sampleapi.domain.inquilino.Inquilino;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducer;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducerMessage;
import dev.alexferreira.sampleapi.infrastructure.kafka.message.InquilinoCreatedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InquilinoProducer implements dev.alexferreira.sampleapi.domain.inquilino.InquilinoProducer {

   private final BaseProducer<String> producer;
   private final ObjectMapper objectMapper;

   @Autowired
   public InquilinoProducer(BaseProducer<String> producer, ObjectMapper objectMapper) {
      this.producer = producer;
      this.objectMapper = objectMapper;
   }

   @Override
   public void send(Inquilino inquilino, String topicName) {

   }
}
