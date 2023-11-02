package dev.alexferreira.sampleapi.domain.tenant;

public interface TenantProducer {

	void send(Tenant tenant, String topicName);
}
