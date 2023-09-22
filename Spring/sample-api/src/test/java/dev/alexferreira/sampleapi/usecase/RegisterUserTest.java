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

   @InjectMocks RegisterUser usecase;

   @AfterEach
   void tearDown() {
      Mockito.verifyNoMoreInteractions();
   }

   @Test
   void shouldBeTransactional() {
      Method executeMethod = Arrays.stream(usecase.getClass().getMethods()).findFirst().get();
   }

   @Test
   void shouldSaveUser_whenInputIsValid() {
   }

   @Test
   void shouldThrowError_whenRepositoryThrow() {
   }
}
