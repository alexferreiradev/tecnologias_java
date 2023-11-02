package dev.alexferreira.sampleapi.usecase;

import dev.alexferreira.sampleapi.common.fixture.DomainFixtures;
import dev.alexferreira.sampleapi.common.fixture.InputFixtures;
import dev.alexferreira.sampleapi.common.test.BaseUnitTests;
import dev.alexferreira.sampleapi.domain.tenant.ImagemTenantStorage;
import dev.alexferreira.sampleapi.domain.tenant.Tenant;
import dev.alexferreira.sampleapi.domain.tenant.TenantProducer;
import dev.alexferreira.sampleapi.domain.tenant.TenantRepository;
import dev.alexferreira.sampleapi.domain.tenant.exception.TenantAlreadyExistsException;
import dev.alexferreira.sampleapi.usecase.input.CreateTenantInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreateTenantTest extends BaseUnitTests {

   private final CreateTenantInput input = InputFixtures.createTenantInput();
   private final String createdTopicName = "created-topic";
   @Mock
   private TenantRepository tenantRepository;
   @Mock
   private ImagemTenantStorage imagemTenantStorage;
   @Mock
   private TenantProducer tenantProducer;

   private CreateTenant useCase;

   @BeforeEach
   void setUp() {
      useCase = new CreateTenant(
              tenantRepository,
              imagemTenantStorage,
              createdTopicName,
              tenantProducer);
   }

   @AfterEach
   void tearDown() {
      Mockito.verifyNoMoreInteractions(tenantRepository, imagemTenantStorage);
   }

   @Test
   void shouldBeAnnotatedByService() {
      assertNotNull(useCase.getClass().getAnnotation(Service.class));
   }

   @Test
   void shouldBeTransactional() {
      Method executeMethod = Arrays.stream(useCase.getClass().getMethods()).findFirst().get();
      assertNotNull(executeMethod.getAnnotation(Transactional.class));
   }

   @Test
   void shouldHaveValueAnottationInTopicField() {
      Constructor<?> constructor = Arrays.stream(useCase.getClass().getConstructors()).findFirst().get();
      Parameter valueTypedVariable = Arrays.stream(constructor.getParameters())
              .filter(parameter -> parameter.isAnnotationPresent(Value.class))
              .findFirst().get();
      Value valueAnnotation = valueTypedVariable.getAnnotation(Value.class);

      assertEquals("${spring.kafka.producer.properties.topics.tenant}", valueAnnotation.value());
   }

   @Test
   void shouldSaveTenant_whenInputIsValid() {
      ArgumentCaptor<Tenant> tenantArgumentCaptor = ArgumentCaptor.forClass(Tenant.class);
      ArgumentCaptor<String> topicArgumentCaptor = ArgumentCaptor.forClass(String.class);
      Mockito.when(tenantRepository.findByDocument(input.document)).thenReturn(Optional.empty());
      Mockito.when(imagemTenantStorage.save(tenantArgumentCaptor.capture(), Mockito.any())).thenReturn("path");
      Mockito.when(tenantRepository.save(tenantArgumentCaptor.capture())).thenAnswer(invocation -> tenantArgumentCaptor.getValue());
      Mockito.doNothing().when(tenantProducer).send(tenantArgumentCaptor.capture(), topicArgumentCaptor.capture());

      useCase.execute(input);

      assertEquals(input.name, tenantArgumentCaptor.getValue().getName());
      assertEquals(input.document, tenantArgumentCaptor.getValue().getDocument());
      assertEquals(createdTopicName, topicArgumentCaptor.getValue());

      Mockito.verify(tenantRepository).findByDocument(input.document);
      Mockito.verify(imagemTenantStorage).save(tenantArgumentCaptor.getAllValues().get(0), input.image);
      Mockito.verify(tenantRepository).save(tenantArgumentCaptor.getAllValues().get(1));
      Mockito.verify(tenantProducer).send(tenantArgumentCaptor.getAllValues().get(2), createdTopicName);
   }

   @Test
   void shouldThrow_whenThereWasTenantByDocument() {
      Optional<Tenant> inquilino = Optional.of(DomainFixtures.createTenant());

      Mockito.when(tenantRepository.findByDocument(input.document)).thenReturn(inquilino);

      assertThrows(TenantAlreadyExistsException.class, () -> useCase.execute(input));

      Mockito.verify(tenantRepository).findByDocument(input.document);
   }

   @Test
   void shouldThrow_whenProducerThrows() {
      ArgumentCaptor<Tenant> tenantArgumentCaptor = ArgumentCaptor.forClass(Tenant.class);
      Mockito.when(tenantRepository.findByDocument(input.document)).thenReturn(Optional.empty());
      Mockito.when(imagemTenantStorage.save(tenantArgumentCaptor.capture(), Mockito.any())).thenReturn("path");
      Mockito.when(tenantRepository.save(tenantArgumentCaptor.capture())).thenAnswer(invocation -> tenantArgumentCaptor.getValue());
      Mockito.doThrow(RuntimeException.class).when(tenantProducer).send(Mockito.any(), Mockito.any());

      assertThrows(RuntimeException.class, () -> useCase.execute(input));

      Mockito.verify(tenantRepository).findByDocument(input.document);
      Mockito.verify(imagemTenantStorage).save(tenantArgumentCaptor.getAllValues().get(0), input.image);
      Mockito.verify(tenantRepository).save(tenantArgumentCaptor.getAllValues().get(1));
      Mockito.verify(tenantRepository).findByDocument(input.document);
      Mockito.verify(tenantProducer).send(Mockito.any(), Mockito.any());
   }

   @Test
   void shouldThrow_whenRepositoryThrows() {
      Mockito.when(tenantRepository.findByDocument(input.document)).thenThrow(new RuntimeException());

      assertThrows(RuntimeException.class, () -> useCase.execute(input));

      Mockito.verify(tenantRepository).findByDocument(input.document);
   }
}
