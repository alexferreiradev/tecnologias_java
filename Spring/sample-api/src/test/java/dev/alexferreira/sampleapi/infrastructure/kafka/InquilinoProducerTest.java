package dev.alexferreira.sampleapi.infrastructure.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexferreira.sampleapi.common.fixture.DomainFixtures;
import dev.alexferreira.sampleapi.common.test.BaseUnitTests;
import dev.alexferreira.sampleapi.domain.inquilino.Inquilino;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducer;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducerMessage;
import dev.alexferreira.sampleapi.infrastructure.kafka.message.InquilinoCreatedMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testcontainers.shaded.org.bouncycastle.asn1.crmf.SinglePubInfo;

import static org.junit.jupiter.api.Assertions.*;

class InquilinoProducerTest extends BaseUnitTests {

   @Mock ObjectMapper objectMapper;
   @Mock BaseProducer<String> baseProducer;
   final String topicName = "topicName";

   InquilinoProducer inquilinoProducer;

   private final Inquilino inquilino = DomainFixtures.createInquilino();

   @BeforeEach
   void setUp() {
      inquilinoProducer = new InquilinoProducer(baseProducer, objectMapper);
   }

   @SuppressWarnings("unchecked")
   @Test
   void shouldSendMessageForTopic() throws JsonProcessingException {
      ArgumentCaptor<InquilinoCreatedMessage> payloadCaptor = ArgumentCaptor.forClass(InquilinoCreatedMessage.class);
      ArgumentCaptor<BaseProducerMessage<String>> messageCaptor = ArgumentCaptor.forClass(BaseProducerMessage.class);
      String message = "message";

      Mockito.when(objectMapper.writeValueAsString(payloadCaptor.capture())).thenReturn(message);
      Mockito.doNothing().when(baseProducer).send(messageCaptor.capture());

      inquilinoProducer.send(inquilino, topicName);

      assertEquals(inquilino.getId().toString(), payloadCaptor.getValue().inquilinoId);
      assertEquals(inquilino.getDocumento(), payloadCaptor.getValue().inquilinoDocumento);
      assertEquals(topicName, messageCaptor.getValue().topicName);
      assertEquals(inquilino.getId().toString(), messageCaptor.getValue().key);
      assertEquals(message, messageCaptor.getValue().message);

      Mockito.verify(objectMapper).writeValueAsString(payloadCaptor.getValue());
      Mockito.verify(baseProducer).send(messageCaptor.getValue());
   }

   @Test
   void shouldThrow_whenBaseProducerThrow() {
      Mockito.doThrow(RuntimeException.class).when(baseProducer).send(Mockito.any());

      assertThrows(RuntimeException.class, () -> inquilinoProducer.send(inquilino, topicName));
   }
}
