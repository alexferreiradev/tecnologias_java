package dev.alexferreira.sampleapi.infrastructure.kafka.base;

public class BaseProducerMessage<T> {

   public String topicKey;
   public T message;
}
