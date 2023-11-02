package dev.alexferreira.sampleapi.infrastructure.storage;

import dev.alexferreira.sampleapi.common.fixture.DomainFixtures;
import dev.alexferreira.sampleapi.common.test.BaseStorageIT;
import dev.alexferreira.sampleapi.domain.tenant.ImagemTenantStorage;
import dev.alexferreira.sampleapi.domain.tenant.Tenant;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AzureStorageIT extends BaseStorageIT {

	private final Tenant tenant = DomainFixtures.createTenant();
	@Autowired
	ImagemTenantStorage azureStorage;

	@Order(0)
	@Test
	void save() {
		String path = azureStorage.save(tenant, "teste".getBytes(StandardCharsets.UTF_8));
		assertNotNull(path);
	}

	@Test
	@Order(1)
	void get() {
		String path = azureStorage.save(tenant, "teste".getBytes(StandardCharsets.UTF_8));
		byte[] bytes = azureStorage.get(tenant);
		assertNotNull(bytes);
	}
}
