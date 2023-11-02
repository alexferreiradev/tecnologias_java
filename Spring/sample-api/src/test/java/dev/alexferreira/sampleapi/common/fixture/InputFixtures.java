package dev.alexferreira.sampleapi.common.fixture;

import dev.alexferreira.sampleapi.common.random.TestRandomValue;
import dev.alexferreira.sampleapi.usecase.input.CreateAuthorizationInput;
import dev.alexferreira.sampleapi.usecase.input.CreateTenantInput;
import dev.alexferreira.sampleapi.usecase.input.RegisterTenantInput;

import java.util.UUID;

public interface InputFixtures {

	static CreateTenantInput createTenantInput() {
		CreateTenantInput input = new CreateTenantInput();
		input.name = "Fake Name";
		input.document = "123123";
		input.image = "Fake Image".getBytes();
		input.tower = "Fake Bloco";
		input.flatNumber = "Fake Apartamento";

		return input;
	}

	static RegisterTenantInput registerTenantInput() {
		RegisterTenantInput input = new RegisterTenantInput();
		input.tenantId = UUID.randomUUID();
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
