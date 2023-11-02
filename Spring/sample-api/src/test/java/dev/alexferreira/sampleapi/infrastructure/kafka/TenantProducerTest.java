package dev.alexferreira.sampleapi.infrastructure.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexferreira.sampleapi.common.fixture.DomainFixtures;
import dev.alexferreira.sampleapi.common.test.BaseUnitTests;
import dev.alexferreira.sampleapi.domain.tenant.Tenant;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducer;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducerMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TenantProducerTest extends BaseUnitTests {

   final String topicName = "topicName";
   private final Tenant tenant = DomainFixtures.createTenant();
   @Mock ObjectMapper objectMapper;
   @Mock BaseProducer<String> baseProducer;
   @InjectMocks TenantProducer tenantProducer;

   @BeforeEach
   void setUp() {
      tenantProducer = new TenantProducer(baseProducer, objectMapper);
   }

   @SuppressWarnings("unchecked")
   @Test
   void shouldSendMessageForTopic() throws JsonProcessingException {
      ArgumentCaptor<BaseProducerMessage<String>> messageCaptor = ArgumentCaptor.forClass(BaseProducerMessage.class);
      Mockito.doNothing().when(baseProducer).send(messageCaptor.capture());
      tenantProducer.send(tenant, topicName);

      Mockito.verify(baseProducer).send(messageCaptor.getValue());
   }

   @Test
   void shouldThrow_whenBaseProducerThrow() {
      Mockito.doThrow(RuntimeException.class).when(baseProducer).send(Mockito.any());

      assertThrows(RuntimeException.class, () -> tenantProducer.send(tenant, topicName));
   }
}
