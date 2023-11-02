package dev.alexferreira.sampleapi.usecase;

import dev.alexferreira.sampleapi.common.fixture.InputFixtures;
import dev.alexferreira.sampleapi.common.test.BaseUnitTests;
import dev.alexferreira.sampleapi.domain.user.User;
import dev.alexferreira.sampleapi.domain.user.UserRepository;
import dev.alexferreira.sampleapi.usecase.input.RegisterTenantInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegisterUserTest extends BaseUnitTests {

   private final RegisterTenantInput input = InputFixtures.registerInquilinoInput();

   @Mock
   private UserRepository repository;

   @InjectMocks RegisterUser usecase;

   @AfterEach
   void tearDown() {
      Mockito.verifyNoMoreInteractions(repository);
   }

   @Test
   void shouldBeAService() {
      assertNotNull(usecase.getClass().getAnnotation(org.springframework.stereotype.Service.class));
   }

   @Test
   void shouldBeTransactional() {
      Method executeMethod = Arrays.stream(usecase.getClass().getMethods()).findFirst().get();
      assertNotNull(executeMethod.getAnnotation(Transactional.class));
   }

   @Test
   void shouldThrowError_whenRepositoryThrow() {
      Mockito.when(repository.save(Mockito.any())).thenThrow(RuntimeException.class);

      assertThrows(RuntimeException.class, () -> usecase.execute(input));
   }

   @Test
   void shouldSaveUser_whenInputIsValid() {
      ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
      Mockito.when(repository.save(argumentCaptor.capture())).thenAnswer(arg -> arg.getArgument(0));

      usecase.execute(input);

      Mockito.verify(repository).save(argumentCaptor.capture());
      assertEquals(input.document, argumentCaptor.getValue().document);
   }
}
