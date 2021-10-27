package dev.alexferreira.sampleproducer;

import dev.alexferreira.sampleproducer.model.CertificateCreationDescriber;
import dev.alexferreira.sampleproducer.model.EventDescriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/certificate")
public class ProducerCertificateApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerCertificateApi.class);

    private final KafkaTemplate<Object, CertificateCreationDescriber> kafkaTemplate;

    @Autowired
    public ProducerCertificateApi(KafkaTemplate<Object, CertificateCreationDescriber> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void startEventCertificatesBuilders(@RequestBody EventDescriber eventDescriber) {
        List<CertificateCreationDescriber> creationDescribers = eventDescriber.participants.stream().map(user -> new CertificateCreationDescriber(user, eventDescriber)).collect(Collectors.toList());
        creationDescribers.forEach(certificateCreationDescriber -> {
            ListenableFuture<SendResult<Object, CertificateCreationDescriber>> createCertificateToEvent = kafkaTemplate.send(TopicsNames.CERTIFICATE, certificateCreationDescriber);
            createCertificateToEvent.addCallback(new ListenableFutureCallback<>() {
                @Override
                public void onFailure(Throwable ex) {
                    LOGGER.error("Erro ao tentar gerar criador de certificado", ex);
                }

                @Override
                public void onSuccess(SendResult<Object, CertificateCreationDescriber> result) {
                    LOGGER.info("Sucesso ao solicitar criador de certificado: {}", result.getRecordMetadata().topic());
                }
            });
        });
    }
}
