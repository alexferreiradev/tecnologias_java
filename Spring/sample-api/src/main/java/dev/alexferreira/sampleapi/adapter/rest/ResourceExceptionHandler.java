package dev.alexferreira.sampleapi.adapter.rest;

import dev.alexferreira.sampleapi.common.exception.DomainException;
import dev.alexferreira.sampleapi.common.exception.response.ErrorEntitityResponse;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
public class ResourceExceptionHandler {

   final Logger logger;

   public ResourceExceptionHandler(Logger logger) {this.logger = logger;}

   @ExceptionHandler(DomainException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public ErrorEntitityResponse handleDomainException(DomainException exception) {
      ErrorEntitityResponse response = new ErrorEntitityResponse();
      response.code = exception.code.code();
      response.message = exception.code.message();

      logger.debug("domain error handled: {}", response.message, exception);

      return response;
   }

   @ExceptionHandler(Throwable.class)
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   public ErrorEntitityResponse handleThrowable(Throwable exception) {
      Map<String, String> detailMap = new HashMap<>();
      detailMap.put("exception", exception.getMessage());

      Optional<StackTraceElement[]> possibleStackTrace = Optional.ofNullable(exception.getStackTrace());
      possibleStackTrace.flatMap(stackTraceElements -> Arrays.stream(stackTraceElements).findFirst())
         .ifPresent(trace -> detailMap.put("stacktrace", trace.getClassName()));

      ErrorEntitityResponse response = new ErrorEntitityResponse();
      response.details = detailMap;

      logger.error(response.message, exception, response);

      return response;
   }

}
