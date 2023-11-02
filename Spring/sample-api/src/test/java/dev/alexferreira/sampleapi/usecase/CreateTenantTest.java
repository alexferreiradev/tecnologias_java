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

   private final CreateTenantInput input = InputFixtures.createInquilinoInput();
   private final String createdTopicName = "created-topic";
   @Mock
   private TenantRepository tenantRepository;
   @Mock
   private ImagemTenantStorage imagemTenantStorage;
   @Mock
   private TenantProducer tenantProducer;
   @InjectMocks
   private CreateTenant useCase;

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

      assertEquals("\\${spring.kafka.producer.properties.topics.tenant}", valueAnnotation.value());
   }

   @Test
   void shouldSaveInquilino_whenInputIsValid() {
      ArgumentCaptor<Tenant> inquilinoArgumentCaptor = ArgumentCaptor.forClass(Tenant.class);
      Mockito.when(tenantRepository.findByDocument(input.document)).thenReturn(Optional.empty());
      Mockito.when(imagemTenantStorage.save(inquilinoArgumentCaptor.capture(), Mockito.any())).thenReturn("path");
      Mockito.when(tenantRepository.save(inquilinoArgumentCaptor.capture())).thenAnswer(invocation -> inquilinoArgumentCaptor.getValue());

      useCase.execute(input);

      assertEquals(input.name, inquilinoArgumentCaptor.getValue().getName());
      assertEquals(input.document, inquilinoArgumentCaptor.getValue().getDocument());

      Mockito.verify(tenantRepository).findByDocument(input.document);
      Mockito.verify(imagemTenantStorage).save(inquilinoArgumentCaptor.getAllValues().get(0), input.image);
      Mockito.verify(tenantRepository).save(inquilinoArgumentCaptor.getAllValues().get(1));
   }

   @Test
   void shouldThrow_whenThereWasInquilinoByDocument() {
      Optional<Tenant> inquilino = Optional.of(DomainFixtures.createInquilino());

      Mockito.when(tenantRepository.findByDocument(input.document)).thenReturn(inquilino);

      assertThrows(TenantAlreadyExistsException.class, () -> useCase.execute(input));

      Mockito.verify(tenantRepository).findByDocument(input.document);
   }

   @Test
   void shouldThrow_whenProducerThrows() {

   }

   @Test
   void shouldThrow_whenRepositoryThrows() {
      Mockito.when(tenantRepository.findByDocument(input.document)).thenThrow(new RuntimeException());

      assertThrows(RuntimeException.class, () -> useCase.execute(input));

      Mockito.verify(tenantRepository).findByDocument(input.document);
   }
}
