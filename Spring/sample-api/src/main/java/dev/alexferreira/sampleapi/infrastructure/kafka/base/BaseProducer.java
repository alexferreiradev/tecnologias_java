package dev.alexferreira.sampleapi.infrastructure.kafka.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class BaseProducer<T> {

   @Autowired KafkaTemplate<String, String> kafkaTemplate;

   public void send(BaseProducerMessage<T> message) {
      try {
         kafkaTemplate.send(message.topicName, message.key, message.message.toString()).get();
      }
      catch(InterruptedException | ExecutionException e) {
         throw new RuntimeException(e);
      }
   }

}
