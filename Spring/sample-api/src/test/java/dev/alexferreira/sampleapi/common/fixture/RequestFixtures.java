package dev.alexferreira.sampleapi.common.fixture;

import dev.alexferreira.sampleapi.adapter.rest.request.CreateInquilinoRequest;

public interface RequestFixtures {

   static CreateInquilinoRequest createInquilinoRequest() {
      CreateInquilinoRequest request = new CreateInquilinoRequest();
      request.nome = "Fake Name";
      request.documento = "123123";
      request.bloco = "Fake Bloco";
      request.apartamento = "Fake Apartamento";

      return request;
   }
}
