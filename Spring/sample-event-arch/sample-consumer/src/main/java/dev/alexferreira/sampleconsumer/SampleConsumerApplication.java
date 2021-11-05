package dev.alexferreira.sampleconsumer;

import dev.alexferreira.sampleconsumer.model.CertificateCreationDescriber;
import dev.alexferreira.sampleconsumer.module.certificate.service.CertificateCreatorService;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;

@SpringBootApplication
public class SampleConsumerApplication {

    public static final String CERTIFICATE_CREATOR_CONSUMER_GROUP_ID = "CERTIFICATE-CREATOR";
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleConsumerApplication.class);
    private final CertificateCreatorService certificateCreatorService;

    public SampleConsumerApplication(CertificateCreatorService certificateCreatorService) {
        this.certificateCreatorService = certificateCreatorService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleConsumerApplication.class, args);
    }

    @KafkaListener(topics = TopicsNames.CERTIFICATE, id = CERTIFICATE_CREATOR_CONSUMER_GROUP_ID)
    public void createPDFOfCertificate(CertificateCreationDescriber describer) {
        LOGGER.info("Iniciando a criacao de PDF de certificado: {}", describer.eventDescriber.name);
        certificateCreatorService.createPDF(describer);
        LOGGER.info("Certificado criado para: {}", describer.eventDescriber.name);
    }

    @Bean
    public RecordMessageConverter converter() {
        return new JsonMessageConverter();
    }

    @Bean
    public NewTopic createTopic() {
        return new NewTopic(TopicsNames.CERTIFICATE, 3, (short) 1);
    }

}
