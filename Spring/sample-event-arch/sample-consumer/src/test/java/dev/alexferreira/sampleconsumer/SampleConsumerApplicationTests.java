package dev.alexferreira.sampleconsumer;

import dev.alexferreira.sampleconsumer.model.CertificateCreationDescriber;
import dev.alexferreira.sampleconsumer.model.EventDescriber;
import dev.alexferreira.sampleconsumer.module.certificate.service.CertificateCreatorService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class SampleConsumerApplicationTests {

    @InjectMocks
    SampleConsumerApplication application;
    @Mock
    CertificateCreatorService creatorService;
    @Mock
    CertificateCreationDescriber describer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void callService() throws Exception {
        describer.eventDescriber = new EventDescriber();
        describer.eventDescriber.name = "teste";

        application.createPDFOfCertificate(describer);

        Mockito.verify(creatorService).createPDF(ArgumentMatchers.argThat(argument -> {
            MatcherAssert.assertThat(argument.eventDescriber.name, CoreMatchers.is("teste"));

            return true;
        }));
    }

}
