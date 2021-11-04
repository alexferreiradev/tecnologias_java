package dev.alexferreira.sampleconsumer;

import dev.alexferreira.sampleconsumer.module.certificate.service.CertificateCreatorService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class ConfigureTestBeans {

    @Bean
    public CertificateCreatorService createServiceWithMock() {
        return Mockito.mock(CertificateCreatorService.class);
    }
}
