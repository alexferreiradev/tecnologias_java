package dev.alexferreira.sampleproducer;

import dev.alexferreira.sampleproducer.model.CertificateCreationDescriber;
import dev.alexferreira.sampleproducer.model.EventDescriber;
import dev.alexferreira.sampleproducer.model.User;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProducerCertificateApiTest {

    @InjectMocks
    ProducerCertificateApi producerCertificateApi;

    @Mock
    KafkaTemplate<String, CertificateCreationDescriber> kafkaTemplateMock;
    @Mock
    ListenableFuture<SendResult<String, CertificateCreationDescriber>> callback;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void startEventCertificatesBuilders() {
        EventDescriber event = new EventDescriber();

        BlockTestUtils.when(() -> {
            event.name = "teste";
            event.participants = new ArrayList<>();
            User userTeste = new User();
            userTeste.name = "teste";
            userTeste.identifier = "testeRG";
            event.participants.add(userTeste);

            Mockito.when(kafkaTemplateMock.send(Mockito.anyString(), Mockito.any())).thenAnswer(invocation -> {
                String topicName = invocation.getArgument(0, String.class);
                CertificateCreationDescriber certificateCreationDescriber = invocation.getArgument(1, CertificateCreationDescriber.class);
                MatcherAssert.assertThat(certificateCreationDescriber.user, Matchers.is(userTeste));
                MatcherAssert.assertThat(certificateCreationDescriber.eventDescriber, Matchers.is(event));
                MatcherAssert.assertThat(topicName, Matchers.is(TopicsNames.CERTIFICATE));

                return callback;
            });
        });

        producerCertificateApi.startEventCertificatesBuilders(event);

        Mockito.verify(kafkaTemplateMock, Mockito.only()).send(Mockito.anyString(), Mockito.any());
    }
}