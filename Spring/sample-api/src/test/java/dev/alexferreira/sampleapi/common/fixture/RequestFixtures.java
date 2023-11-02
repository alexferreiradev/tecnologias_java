package dev.alexferreira.sampleapi.common.fixture;

import dev.alexferreira.sampleapi.adapter.rest.request.CreateAuthorizationRequest;
import dev.alexferreira.sampleapi.adapter.rest.request.CreateTenantRequest;
import dev.alexferreira.sampleapi.common.random.TestRandomValue;

public interface RequestFixtures {

	static CreateTenantRequest createTenantRequest() {
		CreateTenantRequest request = new CreateTenantRequest();
		request.name = "Fake Name";
		request.document = "123123";
		request.tower = "Fake Bloco";
		request.flatNumber = "Fake Apartamento";

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
