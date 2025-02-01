package dev.alexferreira.sampleapi.infrastructure.kafka;

import dev.alexferreira.sampleapi.common.test.BaseKafkaIT;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducer;
import dev.alexferreira.sampleapi.infrastructure.kafka.base.BaseProducerMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseProducerIT extends BaseKafkaIT {

	@Autowired
	BaseProducer<String> baseProducer;

	@BeforeEach
	void setUp() {
		startKafka();
	}

	@Test
	void shouldIncrementOffset_whenProducerSendMessage() {
		long lastPosition = getLastOffset();
		BaseProducerMessage<String> message = new BaseProducerMessage<>();
		message.topicName = getTestTopicName();
		message.key = "2";
		message.message = "message";
		System.out.println("message = " + message);
		baseProducer.send(message);

		long newPosition = getLastOffset();
		assertEquals(lastPosition + 1, newPosition);
	}
}
