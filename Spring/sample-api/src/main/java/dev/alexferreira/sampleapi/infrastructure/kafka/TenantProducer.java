package dev.alexferreira.sampleapi.infrastructure.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexferreira.sampleapi.domain.tenant.Tenant;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantProducer implements dev.alexferreira.sampleapi.domain.tenant.TenantProducer {

	private final BaseProducer<String> producer;
	private final ObjectMapper objectMapper;

	@Autowired
	public TenantProducer(BaseProducer<String> producer, ObjectMapper objectMapper) {
		this.producer = producer;
		this.objectMapper = objectMapper;
	}

	@Override
	public void send(Tenant tenant, String topicName) {

	}
}
