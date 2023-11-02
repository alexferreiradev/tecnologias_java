package dev.alexferreira.sampleapi.adapter.rest.request;

import dev.alexferreira.sampleapi.usecase.input.CreateTenantInput;

public class CreateTenantRequest {

	public String nome;
	public String bloco;
	public String apartamento;
	public String documento;

	public CreateTenantInput toInput() {
		CreateTenantInput input = new CreateTenantInput();
		input.name = nome;
		input.document = documento;
		input.tower = bloco;
		input.flatNumber = apartamento;

		return input;
	}
}
