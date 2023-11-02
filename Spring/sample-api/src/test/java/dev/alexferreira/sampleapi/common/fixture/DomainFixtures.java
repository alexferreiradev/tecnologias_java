package dev.alexferreira.sampleapi.common.fixture;

import dev.alexferreira.sampleapi.common.random.TestRandomValue;
import dev.alexferreira.sampleapi.domain.authorization.Authorization;
import dev.alexferreira.sampleapi.domain.tenant.Tenant;
import dev.alexferreira.sampleapi.domain.user.User;

import java.util.UUID;

public interface DomainFixtures {

	static Tenant createTenant() {
		Tenant tenant = new Tenant();
		tenant.setName("Fake Name");
		tenant.setFlatNumber("101");
		tenant.setTower("A");
		tenant.setDocument(TestRandomValue.generateCpf());
		tenant.setImagePath("path/to/image");

		return tenant;
	}

	static User createUser() {
		User user = new User();
		user.id = UUID.randomUUID().toString();
		user.document = TestRandomValue.generateCpf();

		return user;
	}

	static Authorization createAuthorization() {
		Authorization authorization = new Authorization();
		authorization.id = UUID.randomUUID().toString();
		authorization.doorDescription = "Fake Description floor gate";
		authorization.doorType = "Gate";

		return authorization;
	}
}
