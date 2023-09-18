package dev.alexferreira.sampleapi.common.fixture;

import dev.alexferreira.sampleapi.common.random.TestRandomValue;
import dev.alexferreira.sampleapi.usecase.input.CreateAuthorizationInput;
import dev.alexferreira.sampleapi.usecase.input.CreateInquilinoInput;
import dev.alexferreira.sampleapi.usecase.input.RegisterInquilinoInput;

import java.util.UUID;

public interface InputFixtures {

   static CreateInquilinoInput createInquilinoInput() {
      CreateInquilinoInput input = new CreateInquilinoInput();
      input.nome = "Fake Name";
      input.documento = "123123";
      input.imagem = "Fake Image".getBytes();
      input.bloco = "Fake Bloco";
      input.apartamento = "Fake Apartamento";

      return input;
   }

   static RegisterInquilinoInput registerInquilinoInput() {
      RegisterInquilinoInput input = new RegisterInquilinoInput();
      input.inquilinoId = UUID.randomUUID();
      input.document = TestRandomValue.generateCpf();

      return input;
   }

   static CreateAuthorizationInput createAuthorizationInput() {
      CreateAuthorizationInput input = new CreateAuthorizationInput();
      input.document = TestRandomValue.generateCpf();
      input.indoorDescription = "Fake Description floor gate";
      input.indoorType = "Gate";

      return input;
   }
}
