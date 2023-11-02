package dev.alexferreira.sampleapi.usecase;

import dev.alexferreira.sampleapi.common.fixture.DomainFixtures;
import dev.alexferreira.sampleapi.common.fixture.InputFixtures;
import dev.alexferreira.sampleapi.common.test.BaseUnitTests;
import dev.alexferreira.sampleapi.domain.authorization.Authorization;
import dev.alexferreira.sampleapi.domain.authorization.AuthorizationRepository;
import dev.alexferreira.sampleapi.domain.authorization.exception.UserNotFoundException;
import dev.alexferreira.sampleapi.domain.tenant.Tenant;
import dev.alexferreira.sampleapi.domain.tenant.TenantRepository;
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
   private final Tenant tenant = DomainFixtures.createTenant();

   @Mock
   AuthorizationRepository repository;
   @Mock
   TenantRepository tenantRepository;
   @Mock
   Logger logger;

   @InjectMocks
   private CreateAuthorization useCase;

   @AfterEach
   void tearDown() {
      Mockito.verifyNoMoreInteractions(repository, tenantRepository, logger);
   }

   @Test
   void shouldBeAService() {
      assertNotNull(CreateAuthorization.class.getAnnotation(org.springframework.stereotype.Service.class));
   }

   @Test
   void shouldSaveAuthorization_whenUserFoundAndAuthorized() {
      ArgumentCaptor<Authorization> authorizationArgumentCaptor = ArgumentCaptor.forClass(Authorization.class);

      Mockito.when(tenantRepository.findByDocument(input.document)).thenReturn(Optional.of(tenant));
      Mockito.when(repository.save(authorizationArgumentCaptor.capture()))
              .thenAnswer(invocation -> {
                 Authorization authorization = authorizationArgumentCaptor.getValue();
                 authorization.id = "1";
                 return authorization;
              });
      String authId = useCase.execute(input);

      Mockito.verify(repository).save(authorizationArgumentCaptor.capture());
      Authorization authorization = authorizationArgumentCaptor.getValue();
      Mockito.verify(tenantRepository).findByDocument(input.document);
      Mockito.verify(logger).debug("Creating authorization for document: {}", input.document);
      Mockito.verify(logger).info("Authorization({}) created for document: {}", authorization.id, input.document);

      assertEquals(authorization.id, authId);
      assertEquals(tenant.getDocument(), authorization.tenantAuthorized.document);
      assertEquals(input.indoorType, authorization.doorType);
      assertEquals(input.indoorDescription, authorization.doorDescription);
   }

   @Test
   void shouldThrow_whenRepositoryThrows() {
      Mockito.when(tenantRepository.findByDocument(input.document)).thenReturn(Optional.of(tenant));
      Mockito.when(repository.save(Mockito.any())).thenThrow(IllegalArgumentException.class);

      assertThrows(IllegalArgumentException.class, () -> useCase.execute(input));

      Mockito.verify(tenantRepository).findByDocument(input.document);
      Mockito.verify(logger).debug("Creating authorization for document: {}", input.document);
   }

   @Test
   void shouldThrowError_whenUserNotFoundAndAuthorized() {
      Mockito.when(tenantRepository.findByDocument(input.document)).thenReturn(Optional.empty());

      assertThrows(UserNotFoundException.class, () -> useCase.execute(input));

      Mockito.verify(tenantRepository).findByDocument(input.document);
      Mockito.verify(logger).debug("Creating authorization for document: {}", input.document);
   }

   @Test
   void shouldThrowError_whenUserRepositoryThrows() {
      Mockito.when(tenantRepository.findByDocument(Mockito.any())).thenThrow(IllegalArgumentException.class);

      assertThrows(IllegalArgumentException.class, () -> useCase.execute(input));

      Mockito.verify(tenantRepository).findByDocument(Mockito.any());
      Mockito.verify(logger).debug("Creating authorization for document: {}", input.document);
   }
}
