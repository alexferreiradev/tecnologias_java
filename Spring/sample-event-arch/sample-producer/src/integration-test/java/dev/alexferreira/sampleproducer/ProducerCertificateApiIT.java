package dev.alexferreira.sampleproducer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.assertj.core.matcher.AssertionMatcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.core.BrokerAddress;
import org.springframework.kafka.test.hamcrest.KafkaMatchers;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.kafka.test.hamcrest.KafkaMatchers.hasKey;
import static org.springframework.kafka.test.hamcrest.KafkaMatchers.hasValue;

@EmbeddedKafka(topics = TopicsNames.CERTIFICATE, brokerProperties = "kafka.properties")
@WebMvcTest(ProducerCertificateApi.class)
class ProducerCertificateApiIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    void startEventCertificatesBuilders(EmbeddedKafkaBroker broker) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/certificate"))
                .andExpect(MockMvcResultMatchers.status().is(200));

        BrokerAddress brokerAddress = broker.getBrokerAddress(0);
        System.out.printf("########################## teste - Broker address %s\n", brokerAddress.toString());
        Map<String, Object> configs = new HashMap<>();
        DefaultKafkaConsumerFactory<String, Object> cf = new DefaultKafkaConsumerFactory<>(configs);
        Consumer<String, Object> consumer = cf.createConsumer();
        broker.consumeFromAnEmbeddedTopic(consumer, TopicsNames.CERTIFICATE);
        ConsumerRecords<String, Object> records = KafkaTestUtils.getRecords(consumer);
        ConsumerRecord<String, Object> firstRecord = records.iterator().next();
        MatcherAssert.assertThat(firstRecord, KafkaMatchers.hasKey("123"));
        MatcherAssert.assertThat(firstRecord, KafkaMatchers.hasValue("123"));
    }
}