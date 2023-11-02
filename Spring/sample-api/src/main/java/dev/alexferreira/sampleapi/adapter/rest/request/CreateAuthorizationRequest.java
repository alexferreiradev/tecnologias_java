package dev.alexferreira.sampleapi.adapter.rest.request;

import dev.alexferreira.sampleapi.usecase.input.CreateAuthorizationInput;

public class CreateAuthorizationRequest {

   public String document;
   public String inDoorType;
   public String inDoorDescription;

   public CreateAuthorizationInput toInput() {
      CreateAuthorizationInput input = new CreateAuthorizationInput();
      input.document = document;
      input.indoorType = inDoorType;
      input.indoorDescription = inDoorDescription;

      return input;
   }
}
