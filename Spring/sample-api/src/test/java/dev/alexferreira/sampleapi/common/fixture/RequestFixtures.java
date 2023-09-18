package dev.alexferreira.sampleapi.common.fixture;

import dev.alexferreira.sampleapi.adapter.rest.request.CreateAuthorizationRequest;
import dev.alexferreira.sampleapi.adapter.rest.request.CreateInquilinoRequest;
import dev.alexferreira.sampleapi.common.random.TestRandomValue;

public interface RequestFixtures {

   static CreateInquilinoRequest createInquilinoRequest() {
      CreateInquilinoRequest request = new CreateInquilinoRequest();
      request.nome = "Fake Name";
      request.documento = "123123";
      request.bloco = "Fake Bloco";
      request.apartamento = "Fake Apartamento";

      return request;
   }

   static CreateAuthorizationRequest createAuthorizationRequest() {
      CreateAuthorizationRequest request = new CreateAuthorizationRequest();
      request.document = TestRandomValue.generateCpf();
      request.indoorType = "Fake Indoor Type";
      request.indoorDescription = "Fake Indoor Description";

      return request;
   }
}
