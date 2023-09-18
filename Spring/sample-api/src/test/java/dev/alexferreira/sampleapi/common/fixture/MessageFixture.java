package dev.alexferreira.sampleapi.common.fixture;

import dev.alexferreira.sampleapi.adapter.kafka.message.InquilinoCreatedMessage;
import dev.alexferreira.sampleapi.common.random.TestRandomValue;

import java.util.UUID;

public interface MessageFixture {

   static InquilinoCreatedMessage inquilinoCreatedMessage() {
      InquilinoCreatedMessage inquilinoCreatedMessage = new InquilinoCreatedMessage();
      inquilinoCreatedMessage.inquilinoId = UUID.randomUUID();
      inquilinoCreatedMessage.inquilinoDocumento = TestRandomValue.generateCpf();

      return inquilinoCreatedMessage;
   }
}
