package dev.alexferreira.sampleapi.infrastructure.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexferreira.sampleapi.domain.tenant.Tenant;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducer;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducerMessage;
import dev.alexferreira.sampleapi.infrastructure.kafka.message.TenantCreatedMessage;
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
		BaseProducerMessage<String> message = new BaseProducerMessage<>();
		message.topicName = topicName;
		message.key = tenant.getDocument();
		TenantCreatedMessage tenantMessage = new TenantCreatedMessage();
		tenantMessage.tenantId = tenant.getId().toString();
		tenantMessage.tenantDocument = tenant.getDocument();
		try {
			message.message = objectMapper.writeValueAsString(tenantMessage);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		producer.send(message);
	}
}
