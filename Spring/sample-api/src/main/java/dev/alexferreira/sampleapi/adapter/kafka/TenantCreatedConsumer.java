package dev.alexferreira.sampleapi.adapter.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexferreira.sampleapi.adapter.kafka.message.TenantCreatedMessage;
import dev.alexferreira.sampleapi.usecase.RegisterUser;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TenantCreatedConsumer {

   final Logger logger;
   final RegisterUser registerTenant;
   final ObjectMapper objectMapper;

   public TenantCreatedConsumer(
           Logger logger, RegisterUser registerTenant, ObjectMapper objectMapper
   ) {
      this.logger = logger;
      this.registerTenant = registerTenant;
      this.objectMapper = objectMapper;
   }

   @KafkaListener(topics = "${spring.kafka.producer.properties.topics.tenant}", groupId = "sample-api")
   public void listen(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key, @Payload String payload) throws JsonProcessingException {
      logger.info("Received message with key {} and payload {}", key, payload);
      TenantCreatedMessage tenantCreatedMessage = objectMapper.readValue(payload, TenantCreatedMessage.class);
      registerTenant.execute(tenantCreatedMessage.toInput());
      logger.info("User({}) registered", tenantCreatedMessage.tenantDocument);
   }
}
