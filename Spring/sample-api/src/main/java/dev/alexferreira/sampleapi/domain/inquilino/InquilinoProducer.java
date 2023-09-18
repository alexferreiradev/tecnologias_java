package dev.alexferreira.sampleapi.domain.inquilino;

public interface InquilinoProducer {

   void send(Inquilino inquilino, String topicName);
}
