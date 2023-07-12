package dev.alexferreira.sampleapi.infrastructure.kafka.base;

import org.springframework.stereotype.Component;

@Component
public class BaseProducer<T> {

   public BaseProducer() {
   }

   public void send(BaseProducerMessage<T> message) {
      // TODO()
   }

}
