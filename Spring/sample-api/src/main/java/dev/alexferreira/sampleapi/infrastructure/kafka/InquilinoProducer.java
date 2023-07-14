package dev.alexferreira.sampleapi.infrastructure.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexferreira.sampleapi.domain.inquilino.Inquilino;
import dev.alexferreira.sampleapi.domain.inquilino.InquilinoCreatedProducer;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducer;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducerMessage;
import dev.alexferreira.sampleapi.infrastructure.kafka.message.InquilinoCreatedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InquilinoProducer implements InquilinoCreatedProducer {

   private final BaseProducer<String> producer;
   private final String topic;
   private final ObjectMapper objectMapper;

   @Autowired
   public InquilinoProducer(
      BaseProducer<String> producer,
      @Value("${spring.kafka.producer.properties.topics.inquilino}")
      String topic,
      ObjectMapper objectMapper
   ) {
      this.producer = producer;
      this.topic = topic;
      this.objectMapper = objectMapper;
   }

   @Override
   public void send(Inquilino inquilino) {
      try {
         InquilinoCreatedMessage payload = createPayload(inquilino);

         producer.send(createMessage(payload));
      }
      catch(JsonProcessingException e) {
         throw new RuntimeException(e);
      }
   }

   private InquilinoCreatedMessage createPayload(Inquilino inquilino) {
      InquilinoCreatedMessage payload = new InquilinoCreatedMessage();
      payload.inquilinoId = inquilino.getId().toString();
      payload.inquilinoDocumento = inquilino.getDocumento();
      return payload;
   }

   private BaseProducerMessage<String> createMessage(InquilinoCreatedMessage payload) throws JsonProcessingException {
      BaseProducerMessage<String> message = new BaseProducerMessage<>();
      message.topicName = topic;
      message.key = payload.inquilinoId;
      message.message = objectMapper.writeValueAsString(payload);
      return message;
   }
}
