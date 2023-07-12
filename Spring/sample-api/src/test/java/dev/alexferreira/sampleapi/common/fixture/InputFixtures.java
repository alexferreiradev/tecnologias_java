package dev.alexferreira.sampleapi.common.fixture;

import dev.alexferreira.sampleapi.usecase.CreateInquilinoInput;

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
}
