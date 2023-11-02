package dev.alexferreira.sampleapi.adapter.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexferreira.sampleapi.usecase.RegisterUser;
import org.slf4j.Logger;

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

   public void listen() throws JsonProcessingException {
   }
}
