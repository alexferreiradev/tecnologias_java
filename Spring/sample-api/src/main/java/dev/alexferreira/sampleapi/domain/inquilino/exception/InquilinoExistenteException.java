package dev.alexferreira.sampleapi.domain.inquilino.exception;

import dev.alexferreira.sampleapi.common.exception.DomainException;

public class InquilinoExistenteException extends DomainException {

   public InquilinoExistenteException() {
      super(InquilinoExceptionCode.DUPLICATED);
   }
}
