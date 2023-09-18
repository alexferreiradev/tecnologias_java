package dev.alexferreira.sampleapi.adapter.kafka.message;

import dev.alexferreira.sampleapi.usecase.input.RegisterInquilinoInput;

import java.util.UUID;

public class InquilinoCreatedMessage {
   public UUID inquilinoId;
   public String inquilinoDocumento;

   public RegisterInquilinoInput toInput() {
      RegisterInquilinoInput input = new RegisterInquilinoInput();
      input.inquilinoId = inquilinoId;
      input.document = inquilinoDocumento;

      return input;
   }
}
