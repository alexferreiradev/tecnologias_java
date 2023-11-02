package dev.alexferreira.sampleapi.usecase;

import dev.alexferreira.sampleapi.common.fixture.InputFixtures;
import dev.alexferreira.sampleapi.common.test.BaseUnitTests;
import dev.alexferreira.sampleapi.usecase.input.RegisterTenantInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.lang.reflect.Method;
import java.util.Arrays;

class RegisterUserTest extends BaseUnitTests {

   private final RegisterTenantInput input = InputFixtures.registerInquilinoInput();

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
