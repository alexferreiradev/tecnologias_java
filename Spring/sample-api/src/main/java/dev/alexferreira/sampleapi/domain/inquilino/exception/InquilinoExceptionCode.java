package dev.alexferreira.sampleapi.domain.inquilino.exception;

import dev.alexferreira.sampleapi.common.exception.DomainExceptionCode;

public enum InquilinoExceptionCode implements DomainExceptionCode {

   DUPLICATED("INQ001", "Inquilino já cadastrado"),
   ;

   private final String code;
   private final String message;

   InquilinoExceptionCode(String code, String message) {
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
