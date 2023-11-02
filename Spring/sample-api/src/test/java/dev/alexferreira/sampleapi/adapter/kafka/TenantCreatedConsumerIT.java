package dev.alexferreira.sampleapi.adapter.kafka;

import dev.alexferreira.sampleapi.adapter.kafka.message.TenantCreatedMessage;
import dev.alexferreira.sampleapi.common.fixture.MessageFixture;
import dev.alexferreira.sampleapi.common.test.BaseKafkaIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;

@Import(TenantCreatedConsumer.class)
class TenantCreatedConsumerIT extends BaseKafkaIT {

	private final TenantCreatedMessage message = MessageFixture.inquilinoCreatedMessage();

	@Value("${spring.kafka.producer.properties.topics.tenant}")
	private String topicName;


	@Test
	void listen() {

	}
}
