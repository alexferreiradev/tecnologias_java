package dev.alexferreira.sampleapi.infrastructure.kafka.base;

public class BaseProducerMessage<T> {

   public String topicName;
   public String key;
   public T message;
}
