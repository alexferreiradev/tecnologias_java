package dev.alexferreira.sampleapi.common.exception;

public abstract class DomainException extends RuntimeException {

   public DomainExceptionCode code;

   public DomainException(DomainExceptionCode code) {
      this.code = code;
   }
}
