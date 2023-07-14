package dev.alexferreira.sampleapi.infrastructure.kafka.message;

import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducerMessage;

public class InquilinoCreatedMessage {

   public String inquilinoId;
   public String inquilinoDocumento;

   public BaseProducerMessage<InquilinoCreatedMessage> toBaseProducerMessage(String topic) {
      BaseProducerMessage<InquilinoCreatedMessage> message = new BaseProducerMessage<>();
      message.topicName = topic;
      message.key = inquilinoId;
      message.message = this;

      return message;
   }
}
