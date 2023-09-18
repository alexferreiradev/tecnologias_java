package dev.alexferreira.sampleapi.adapter.rest.request;

import dev.alexferreira.sampleapi.usecase.input.CreateAuthorizationInput;

public class CreateAuthorizationRequest {

   public String document;
   public String indoorType;
   public String indoorDescription;

   public CreateAuthorizationInput toInput() {
      CreateAuthorizationInput input = new CreateAuthorizationInput();
      input.document = document;
      input.indoorType = indoorType;
      input.indoorDescription = indoorDescription;

      return input;
   }
}
