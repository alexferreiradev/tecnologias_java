package dev.alexferreira.sampleapi.adapter.kafka.message;

import dev.alexferreira.sampleapi.usecase.input.RegisterTenantInput;

import java.util.UUID;

public class TenantCreatedMessage {
   public UUID tenantId;
   public String tenantDocument;

   public RegisterTenantInput toInput() {
      RegisterTenantInput input = new RegisterTenantInput();
      input.document = tenantDocument;

      return input;
   }
}
