package dev.alexferreira.sampleapi.usecase;

import dev.alexferreira.sampleapi.common.fixture.InputFixtures;
import dev.alexferreira.sampleapi.common.test.BaseUnitTests;
import dev.alexferreira.sampleapi.domain.inquilino.Inquilino;
import dev.alexferreira.sampleapi.domain.inquilino.InquilinoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateInquilinoTest extends BaseUnitTests {

   @Mock
   private InquilinoRepository inquilinoRepository;

   @InjectMocks
   private CreateInquilino useCase;

   private final CreateInquilinoInput input = InputFixtures.createInquilinoInput();

   @AfterEach
   void tearDown() {
      Mockito.verifyNoMoreInteractions(inquilinoRepository);
   }

   @Test
   void shouldBeAnnotatedByService() {
      assertNotNull(CreateInquilino.class.getAnnotation(org.springframework.stereotype.Service.class));
   }

   @Test
   void shouldSaveInquilino_whenInputIsValid() {
      ArgumentCaptor<Inquilino> inquilinoArgumentCaptor = ArgumentCaptor.forClass(Inquilino.class);
      Mockito.when(inquilinoRepository.findByDocumento(input.documento)).thenReturn(Optional.empty());
      Mockito.when(inquilinoRepository.save(inquilinoArgumentCaptor.capture())).thenAnswer(invocation -> inquilinoArgumentCaptor.getValue());

      useCase.execute(input);

      assertEquals(input.nome, inquilinoArgumentCaptor.getValue().getNome());
      assertEquals(input.documento, inquilinoArgumentCaptor.getValue().getDocumento());

      Mockito.verify(inquilinoRepository).findByDocumento(input.documento);
      Mockito.verify(inquilinoRepository).save(inquilinoArgumentCaptor.getValue());
   }

   @Test
   void shouldThrow_whenRepositoryThrows() {
      Mockito.when(inquilinoRepository.findByDocumento(input.documento)).thenReturn(Optional.empty());
      Mockito.when(inquilinoRepository.save(Mockito.any(Inquilino.class))).thenThrow(new RuntimeException());

      assertThrows(RuntimeException.class, () -> useCase.execute(input));

      Mockito.verify(inquilinoRepository).findByDocumento(input.documento);
      Mockito.verify(inquilinoRepository).save(Mockito.any(Inquilino.class));
   }
}
