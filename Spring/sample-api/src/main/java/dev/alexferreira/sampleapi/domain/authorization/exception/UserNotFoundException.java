package dev.alexferreira.sampleapi.domain.authorization.exception;

import dev.alexferreira.sampleapi.common.exception.DomainException;

public class UserNotFoundException extends DomainException {
   public UserNotFoundException() {
      super(AuthorizationExceptionCode.USER_NOT_FOUND);
   }
}
