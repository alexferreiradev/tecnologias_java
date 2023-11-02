package dev.alexferreira.sampleapi.common.fixture;

import dev.alexferreira.sampleapi.adapter.kafka.message.TenantCreatedMessage;
import dev.alexferreira.sampleapi.common.random.TestRandomValue;

import java.util.UUID;

public interface MessageFixture {

	static TenantCreatedMessage inquilinoCreatedMessage() {
		TenantCreatedMessage tenantCreatedMessage = new TenantCreatedMessage();
		tenantCreatedMessage.tenantId = UUID.randomUUID();
		tenantCreatedMessage.tenantDocument = TestRandomValue.generateCpf();

		return tenantCreatedMessage;
	}
}
