package dev.alexferreira.sampleapi.adapter.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexferreira.sampleapi.adapter.kafka.message.InquilinoCreatedMessage;
import dev.alexferreira.sampleapi.usecase.RegisterUser;
import dev.alexferreira.sampleapi.usecase.input.RegisterInquilinoInput;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

public class InquilinoCreatedConsumer {

   final Logger logger;
   final RegisterUser registerInquilino;
   final ObjectMapper objectMapper;

   public InquilinoCreatedConsumer(
      Logger logger, RegisterUser registerInquilino, ObjectMapper objectMapper
   ) {this.logger = logger;
      this.registerInquilino = registerInquilino;
      this.objectMapper = objectMapper;
   }

   public void listen() throws JsonProcessingException {
   }
}
