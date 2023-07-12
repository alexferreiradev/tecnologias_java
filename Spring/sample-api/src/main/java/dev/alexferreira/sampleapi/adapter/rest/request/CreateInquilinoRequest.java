package dev.alexferreira.sampleapi.adapter.rest.request;

import dev.alexferreira.sampleapi.usecase.CreateInquilinoInput;

public class CreateInquilinoRequest {

   public String nome;
   public String bloco;
   public String apartamento;
   public String documento;

   public CreateInquilinoInput toInput() {
      CreateInquilinoInput input = new CreateInquilinoInput();
      input.nome = nome;
      input.documento = documento;
      input.bloco = bloco;
      input.apartamento = apartamento;

      return input;
   }
}
