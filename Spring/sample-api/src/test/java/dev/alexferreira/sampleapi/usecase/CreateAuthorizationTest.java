package dev.alexferreira.sampleapi.usecase;

import dev.alexferreira.sampleapi.common.fixture.DomainFixtures;
import dev.alexferreira.sampleapi.common.fixture.InputFixtures;
import dev.alexferreira.sampleapi.common.test.BaseUnitTests;
import dev.alexferreira.sampleapi.domain.authorization.Authorization;
import dev.alexferreira.sampleapi.domain.authorization.AuthorizationRepository;
import dev.alexferreira.sampleapi.domain.authorization.exception.UserNotFoundException;
import dev.alexferreira.sampleapi.domain.user.User;
import dev.alexferreira.sampleapi.domain.user.UserRepository;
import dev.alexferreira.sampleapi.usecase.input.CreateAuthorizationInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreateAuthorizationTest extends BaseUnitTests {

   private final CreateAuthorizationInput input = InputFixtures.createAuthorizationInput();
   private final User user = DomainFixtures.createUser();

   @Mock AuthorizationRepository repository;
   @Mock UserRepository userRepository;
   @Mock Logger logger;

   @InjectMocks
   private CreateAuthorization useCase;

   @AfterEach
   void tearDown() {
      Mockito.verifyNoMoreInteractions(repository, userRepository, logger);
   }

   @Test
   void shouldBeAService() {
      assertNotNull(CreateAuthorization.class.getAnnotation(org.springframework.stereotype.Service.class));
   }

   @Test
   void shouldSaveAuthorization_whenUserFoundAndAuthorized() {
      ArgumentCaptor<Authorization> authorizationArgumentCaptor = ArgumentCaptor.forClass(Authorization.class);

      Mockito.when(userRepository.findByDocument(input.document)).thenReturn(Optional.of(user));
      Mockito.when(repository.save(authorizationArgumentCaptor.capture())).thenAnswer(arg -> authorizationArgumentCaptor.getValue());

      String authId = useCase.execute(input);

      Mockito.verify(repository).save(authorizationArgumentCaptor.capture());
      Mockito.verify(logger).debug("Creating authorization for document: {}", input.document);
      Mockito.verify(logger).info("Authorization({}) created for document: {}", authId, input.document);

      assertEquals(authorizationArgumentCaptor.getValue().id, authId);
      assertEquals(user.document, authorizationArgumentCaptor.getValue().userAuthorized.document);
      assertEquals(input.indoorType, authorizationArgumentCaptor.getValue().doorType);
      assertEquals(input.indoorDescription, authorizationArgumentCaptor.getValue().doorDescription);
   }

   @Test
   void shouldThrow_whenRepositoryThrows() {
      Mockito.when(userRepository.findByDocument(input.document)).thenReturn(Optional.of(user));
      Mockito.when(repository.save(Mockito.any())).thenThrow(IllegalArgumentException.class);

      assertThrows(IllegalArgumentException.class, () -> useCase.execute(input));

      Mockito.verify(userRepository).findByDocument(input.document);
      Mockito.verify(logger).debug("Creating authorization for document: {}", input.document);
   }

   @Test
   void shouldThrowError_whenUserNotFoundAndAuthorized() {
      Mockito.when(userRepository.findByDocument(input.document)).thenReturn(Optional.empty());

      assertThrows(UserNotFoundException.class, () -> useCase.execute(input));

      Mockito.verify(userRepository).findByDocument(input.document);
      Mockito.verify(logger).debug("Creating authorization for document: {}", input.document);
   }

   @Test
   void shouldThrowError_whenUserRepositoryThrows() {
      Mockito.when(userRepository.findByDocument(Mockito.any())).thenThrow(IllegalArgumentException.class);

      assertThrows(IllegalArgumentException.class, () -> useCase.execute(input));

      Mockito.verify(userRepository).findByDocument(Mockito.any());
      Mockito.verify(logger).debug("Creating authorization for document: {}", input.document);
   }
}
