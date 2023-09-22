package dev.alexferreira.sampleapi.usecase;

import dev.alexferreira.sampleapi.common.fixture.DomainFixtures;
import dev.alexferreira.sampleapi.common.fixture.InputFixtures;
import dev.alexferreira.sampleapi.common.test.BaseUnitTests;
import dev.alexferreira.sampleapi.domain.inquilino.ImagemInquilinoStorage;
import dev.alexferreira.sampleapi.domain.inquilino.Inquilino;
import dev.alexferreira.sampleapi.domain.inquilino.InquilinoProducer;
import dev.alexferreira.sampleapi.domain.inquilino.InquilinoRepository;
import dev.alexferreira.sampleapi.domain.inquilino.exception.InquilinoExistenteException;
import dev.alexferreira.sampleapi.usecase.input.CreateInquilinoInput;
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
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateInquilinoTest extends BaseUnitTests {

   @Mock
   private InquilinoRepository inquilinoRepository;
   @Mock
   private ImagemInquilinoStorage imagemInquilinoStorage;
   @Mock
   private InquilinoProducer inquilinoProducer;
   private final String createdTopicName = "created-topic";

   @InjectMocks
   private CreateInquilino useCase;

   private final CreateInquilinoInput input = InputFixtures.createInquilinoInput();

   @AfterEach
   void tearDown() {
      Mockito.verifyNoMoreInteractions(inquilinoRepository, imagemInquilinoStorage);
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
      Constructor constructor = Arrays.stream(useCase.getClass().getConstructors()).findFirst().get();
      Value annotation = constructor.getParameters()[3].getAnnotation(Value.class);

   }

   @Test
   void shouldSaveInquilino_whenInputIsValid() {
      ArgumentCaptor<Inquilino> inquilinoArgumentCaptor = ArgumentCaptor.forClass(Inquilino.class);
      Mockito.when(inquilinoRepository.findByDocumento(input.documento)).thenReturn(Optional.empty());
      Mockito.when(imagemInquilinoStorage.save(inquilinoArgumentCaptor.capture(), Mockito.any())).thenReturn("path");
      Mockito.when(inquilinoRepository.save(inquilinoArgumentCaptor.capture())).thenAnswer(invocation -> inquilinoArgumentCaptor.getValue());

      useCase.execute(input);

      assertEquals(input.nome, inquilinoArgumentCaptor.getValue().getNome());
      assertEquals(input.documento, inquilinoArgumentCaptor.getValue().getDocumento());

      Mockito.verify(inquilinoRepository).findByDocumento(input.documento);
      Mockito.verify(imagemInquilinoStorage).save(inquilinoArgumentCaptor.getAllValues().get(0), input.imagem);
      Mockito.verify(inquilinoRepository).save(inquilinoArgumentCaptor.getAllValues().get(1));
   }

   @Test
   void shouldThrow_whenThereWasInquilinoByDocument() {
      Optional<Inquilino> inquilino = Optional.of(DomainFixtures.createInquilino());

      Mockito.when(inquilinoRepository.findByDocumento(input.documento)).thenReturn(inquilino);

      assertThrows(InquilinoExistenteException.class, () -> useCase.execute(input));

      Mockito.verify(inquilinoRepository).findByDocumento(input.documento);
   }

   @Test
   void shouldThrow_whenProducerThrows() {

   }

   @Test
   void shouldThrow_whenRepositoryThrows() {
      Mockito.when(inquilinoRepository.findByDocumento(input.documento)).thenThrow(new RuntimeException());

      assertThrows(RuntimeException.class, () -> useCase.execute(input));

      Mockito.verify(inquilinoRepository).findByDocumento(input.documento);
   }
}
