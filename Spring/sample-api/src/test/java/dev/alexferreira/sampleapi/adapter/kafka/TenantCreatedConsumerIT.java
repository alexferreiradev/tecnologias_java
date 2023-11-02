package dev.alexferreira.sampleapi.adapter.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.alexferreira.sampleapi.adapter.kafka.message.TenantCreatedMessage;
import dev.alexferreira.sampleapi.common.fixture.MessageFixture;
import dev.alexferreira.sampleapi.common.test.BaseKafkaIT;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducerMessage;
import dev.alexferreira.sampleapi.usecase.RegisterUser;
import dev.alexferreira.sampleapi.usecase.input.RegisterTenantInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.KafkaListener;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Import(TenantCreatedConsumer.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
@Execution(ExecutionMode.SAME_THREAD)
class TenantCreatedConsumerIT extends BaseKafkaIT {

   private final TenantCreatedMessage message = MessageFixture.inquilinoCreatedMessage();

   @Value("${spring.kafka.producer.properties.topics.tenant}") private String topicName;

   @MockBean private RegisterUser registerUser;

   @MockBean private Logger logger;

   @BeforeEach
   void setUp() {
      Mockito.clearInvocations(registerUser, logger);
   }

   @AfterEach
   void tearDown() {
      Mockito.verifyNoMoreInteractions(registerUser, logger);
   }

   @Test
   void shouldBeComponentClass() {
      assertNotNull(TenantCreatedConsumer.class.getAnnotation(org.springframework.stereotype.Component.class));
   }

   @Test
   void shouldHaveAMethodForKafkaListener() {
      Method executeMethod = Arrays.stream(TenantCreatedConsumer.class.getMethods()).findFirst().get();
      KafkaListener methodAnnotation = executeMethod.getAnnotation(KafkaListener.class);
      assertNotNull(methodAnnotation);
      assertEquals("sample-api", methodAnnotation.groupId());
      assertEquals(
         "${spring.kafka.producer.properties.topics.tenant}",
         Arrays.stream(methodAnnotation.topics()).findFirst().get()
      );
   }

   @Test
   void shouldRetry_whenUseCaseThrows() throws JsonProcessingException {
      Mockito.doThrow(RuntimeException.class).when(registerUser).execute(Mockito.any());

      sendMessage(message, topicName);

      Mockito.verify(registerUser, Mockito.timeout(30_000).times(3)).execute(Mockito.any(RegisterTenantInput.class));
      Mockito.verify(logger, Mockito.times(3)).info("Received message with key {} and payload {}",
         message.tenantId.toString(),
         objectMapper.writeValueAsString(message)
      );
   }

   @Test
   void shouldConsumeMessage_whenMessageIsValid() throws JsonProcessingException {
      ArgumentCaptor<RegisterTenantInput> argCaptor = ArgumentCaptor.forClass(RegisterTenantInput.class);

      sendMessage(message, topicName);

      Mockito.verify(registerUser, Mockito.timeout(30_000)).execute(argCaptor.capture());
      Mockito.verify(logger).info("Received message with key {} and payload {}",
         message.tenantId.toString(),
         objectMapper.writeValueAsString(message)
      );
      Mockito.verify(logger).info("User({}) registered", message.tenantDocument);

      assertEquals(message.tenantDocument, argCaptor.getValue().document);
   }

   private void sendMessage(TenantCreatedMessage message, String topicName) throws JsonProcessingException {
      BaseProducerMessage<String> baseMsg = new BaseProducerMessage<>();
      baseMsg.topicName = topicName;
      baseMsg.key = message.tenantId.toString();
      baseMsg.message = objectMapper.writeValueAsString(message);
      baseProducer.send(baseMsg);
   }
}
