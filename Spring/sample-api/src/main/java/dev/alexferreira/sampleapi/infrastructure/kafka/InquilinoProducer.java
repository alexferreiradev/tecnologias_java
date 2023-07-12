package dev.alexferreira.sampleapi.infrastructure.kafka;

import dev.alexferreira.sampleapi.domain.inquilino.Inquilino;
import dev.alexferreira.sampleapi.domain.inquilino.InquilinoCreatedProducer;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducer;
import org.springframework.stereotype.Component;

@Component
public class InquilinoProducer implements InquilinoCreatedProducer {

   BaseProducer<String> producer;

   public InquilinoProducer(BaseProducer<String> producer) {
      this.producer = producer;
   }

   @Override
   public void send(Inquilino inquilino) {
      // TODO()
   }
}
