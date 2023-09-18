package dev.alexferreira.sampleapi.domain.authorization.exception;

import dev.alexferreira.sampleapi.common.exception.DomainExceptionCode;

public enum AuthorizationExceptionCode implements DomainExceptionCode {
   USER_NOT_FOUND("AUTH001", "User not found");

   private final String message;
   private final String code;

   AuthorizationExceptionCode(String code, String message) {
      this.message = message;
      this.code = code;
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
