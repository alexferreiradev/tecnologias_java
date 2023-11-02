package dev.alexferreira.sampleapi.common.fixture;

import dev.alexferreira.sampleapi.adapter.rest.request.CreateAuthorizationRequest;
import dev.alexferreira.sampleapi.adapter.rest.request.CreateTenantRequest;
import dev.alexferreira.sampleapi.common.random.TestRandomValue;

public interface RequestFixtures {

	static CreateTenantRequest createInquilinoRequest() {
		CreateTenantRequest request = new CreateTenantRequest();
		request.nome = "Fake Name";
		request.documento = "123123";
		request.bloco = "Fake Bloco";
		request.apartamento = "Fake Apartamento";

		return request;
	}

	static CreateAuthorizationRequest createAuthorizationRequest() {
		CreateAuthorizationRequest request = new CreateAuthorizationRequest();
		request.document = TestRandomValue.generateCpf();
		request.inDoorType = "Fake Indoor Type";
		request.inDoorDescription = "Fake Indoor Description";

		return request;
	}
}
