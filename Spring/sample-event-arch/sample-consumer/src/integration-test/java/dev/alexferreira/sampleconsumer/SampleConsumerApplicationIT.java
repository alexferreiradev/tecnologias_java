package dev.alexferreira.sampleconsumer;

import dev.alexferreira.sampleconsumer.model.CertificateCreationDescriber;
import dev.alexferreira.sampleconsumer.model.EventDescriber;
import dev.alexferreira.sampleconsumer.model.User;
import dev.alexferreira.sampleconsumer.module.certificate.service.CertificateCreatorService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.hamcrest.MockitoHamcrest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Objects;

@SpringBootTest
@EmbeddedKafka
@ActiveProfiles("test")
class SampleConsumerApplicationIT {
    @Autowired
    CertificateCreatorService creatorService;

    @Autowired
    KafkaTemplate<String, CertificateCreationDescriber> kafkaTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPDFOfCertificate() throws Exception {
        CertificateCreationDescriber describer = new CertificateCreationDescriber();
        BlockTestUtils.when(() -> {
            describer.user = new User();
            describer.eventDescriber = new EventDescriber();
            describer.user.name = "teste name";
            describer.user.identifier = "teste identifier";
        });

        kafkaTemplate.send(TopicsNames.CERTIFICATE, describer);
        Mockito.verify(creatorService, Mockito.timeout(60_000).times(1)).createPDF(ArgumentMatchers.argThat(describerArg -> {
            MatcherAssert.assertThat(describerArg.user.name, CoreMatchers.is("teste name"));

            return true;
        }));
    }

}