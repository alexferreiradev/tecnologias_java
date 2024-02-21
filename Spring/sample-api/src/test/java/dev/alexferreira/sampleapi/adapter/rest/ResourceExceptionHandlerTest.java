package dev.alexferreira.sampleapi.adapter.rest;

import dev.alexferreira.sampleapi.common.exception.DomainException;
import dev.alexferreira.sampleapi.common.exception.DomainExceptionCode;
import dev.alexferreira.sampleapi.common.exception.response.ErrorEntitityResponse;
import dev.alexferreira.sampleapi.common.test.BaseUnitTests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class ResourceExceptionHandlerTest extends BaseUnitTests {

   @Mock private Logger logger;

   @InjectMocks private ResourceExceptionHandler resourceExceptionHandler;

   @AfterEach
   void tearDown() {
      Mockito.verifyNoMoreInteractions(logger);
   }

   @Test
   void shouldReturnErrorResponse_whenThrowableIsGeneric() {
      Throwable throwable = new Throwable("teste");
      ArgumentCaptor<Object> objectArgumentCaptor = ArgumentCaptor.forClass(Object.class);

      ErrorEntitityResponse response = resourceExceptionHandler.handleThrowable(throwable);

      assertEquals("GENERIC_ERROR", response.code);
      assertEquals("Erro interno", response.message);
      assertEquals(throwable.getMessage(), response.details.get("exception"));
      assertEquals(throwable.getStackTrace()[0].getClassName(), response.details.get("stacktrace"));

      Mockito.verify(logger).error(Mockito.eq(response.message), Mockito.eq(throwable), objectArgumentCaptor.capture());
      assertEquals(response, objectArgumentCaptor.getValue());
   }

   @Test
   void shouldReturnErrorResponseWithDetails_whenThrowableHasNoStackTrace() {
      Throwable throwable = new Throwable("teste");
      throwable.setStackTrace(new StackTraceElement[] {});
      ArgumentCaptor<Object> objectArgumentCaptor = ArgumentCaptor.forClass(Object.class);

      ErrorEntitityResponse response = resourceExceptionHandler.handleThrowable(throwable);

      assertEquals("GENERIC_ERROR", response.code);
      assertEquals("Erro interno", response.message);
      assertEquals(throwable.getMessage(), response.details.get("exception"));
      assertNull(response.details.get("stacktrace"));

      Mockito.verify(logger).error(Mockito.eq(response.message), Mockito.eq(throwable), objectArgumentCaptor.capture());
      assertEquals(response, objectArgumentCaptor.getValue());
   }

   @Test
   void shouldReturnErrorResponse_whenThrowableIsDomainException() {
      DomainException domainException = new TestDomainException();

      ErrorEntitityResponse response = resourceExceptionHandler.handleDomainException(domainException);

      assertEquals(TestDomainExceptionCode.TESTE.code, response.code);
      assertEquals(TestDomainExceptionCode.TESTE.message, response.message);
      assertNull(response.details);

      Mockito.verify(logger).debug("domain error handled: {}", response.message, domainException);
   }

   protected enum TestDomainExceptionCode implements DomainExceptionCode {
      TESTE("TESTE", "teste");

      private final String code;
      private final String message;

      TestDomainExceptionCode(String code, String message) {
         this.code = code;
         this.message = message;
      }

      @Override
      public String code() {
         return code;
      }

      @Override
      public String message() {
         return message;
      }
   }

   protected static class TestDomainException extends DomainException {

      public TestDomainException() {
         super(TestDomainExceptionCode.TESTE);
      }
   }
}
