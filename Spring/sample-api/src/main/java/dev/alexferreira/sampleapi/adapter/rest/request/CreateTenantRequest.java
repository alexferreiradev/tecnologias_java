package dev.alexferreira.sampleapi.adapter.rest.request;

import dev.alexferreira.sampleapi.usecase.input.CreateTenantInput;

public class CreateTenantRequest {

	public String name;
	public String tower;
	public String flatNumber;
	public String document;

	public CreateTenantInput toInput() {
		CreateTenantInput input = new CreateTenantInput();
		input.name = name;
		input.document = document;
		input.tower = tower;
		input.flatNumber = flatNumber;

		return input;
	}
}
