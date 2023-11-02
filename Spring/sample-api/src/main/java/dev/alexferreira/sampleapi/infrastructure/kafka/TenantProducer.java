package dev.alexferreira.sampleapi.infrastructure.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexferreira.sampleapi.adapter.kafka.message.TenantCreatedMessage;
import dev.alexferreira.sampleapi.domain.tenant.Tenant;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducer;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducerMessage;
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
		try {
			TenantCreatedMessage payload = createPayload(tenant);

			producer.send(createMessage(payload, topicName));
		}
		catch(JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private TenantCreatedMessage createPayload(Tenant tenant) {
		TenantCreatedMessage payload = new TenantCreatedMessage();
		payload.tenantId = tenant.getId();
		payload.tenantDocument = tenant.getDocument();

		return payload;
	}

	private BaseProducerMessage<String> createMessage(
		TenantCreatedMessage payload,
		String topicName
	) throws JsonProcessingException {
		BaseProducerMessage<String> message = new BaseProducerMessage<>();
		message.topicName = topicName;
		message.key = payload.tenantId.toString();
		message.message = objectMapper.writeValueAsString(payload);
		return message;
	}
}
