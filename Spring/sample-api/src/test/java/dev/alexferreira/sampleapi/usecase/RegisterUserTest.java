package dev.alexferreira.sampleapi.usecase;

import dev.alexferreira.sampleapi.common.fixture.InputFixtures;
import dev.alexferreira.sampleapi.common.test.BaseUnitTests;
import dev.alexferreira.sampleapi.domain.user.User;
import dev.alexferreira.sampleapi.domain.user.UserRepository;
import dev.alexferreira.sampleapi.usecase.input.RegisterInquilinoInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.transaction.Transactional;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegisterUserTest extends BaseUnitTests {

   private final RegisterInquilinoInput input = InputFixtures.registerInquilinoInput();

   @Mock UserRepository repository;
   @InjectMocks RegisterUser usecase;

   @AfterEach
   void tearDown() {
      Mockito.verifyNoMoreInteractions(repository);
   }

   @Test
   void shouldBeTransactional() {
      Method executeMethod = Arrays.stream(usecase.getClass().getMethods()).findFirst().get();
      assertNotNull(executeMethod.getAnnotation(Transactional.class));
   }

   @Test
   void shouldSaveUser_whenInputIsValid() {
      ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

      usecase.execute(input);

      Mockito.verify(repository).save(userCaptor.capture());

      assertEquals(input.inquilinoId.toString(), userCaptor.getValue().id);
      assertEquals(input.document, userCaptor.getValue().document);
   }

   @Test
   void shouldThrowError_whenRepositoryThrow() {
      Mockito.when(repository.save(Mockito.any())).thenThrow(IllegalArgumentException.class);

      assertThrows(IllegalArgumentException.class, () -> usecase.execute(input));

      Mockito.verify(repository).save(Mockito.any());
   }
}
