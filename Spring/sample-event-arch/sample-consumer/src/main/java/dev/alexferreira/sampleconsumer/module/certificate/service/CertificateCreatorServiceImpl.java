package dev.alexferreira.sampleconsumer.module.certificate.service;

import dev.alexferreira.sampleconsumer.model.CertificateCreationDescriber;
import dev.alexferreira.sampleconsumer.module.certificate.command.CertificatorGeneratorCommand;
import dev.alexferreira.sampleconsumer.module.certificate.model.Certificate;
import dev.alexferreira.sampleconsumer.module.certificate.service.exporter.CertificateExporter;
import dev.alexferreira.sampleconsumer.module.certificate.service.generator.CertificateGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Profile({"dev", "prod"})
public class CertificateCreatorServiceImpl implements CertificateCreatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CertificateCreatorServiceImpl.class);

    private final CertificateGenerator certificateGenerator;
    private final CertificateExporter certificateExporter;

    public CertificateCreatorServiceImpl(CertificateGenerator certificateGenerator, CertificateExporter certificateExporter) {
        this.certificateGenerator = certificateGenerator;
        this.certificateExporter = certificateExporter;
    }

    @Override
    public void createPDF(CertificateCreationDescriber describer) {
        LOGGER.info("Criando certificado para: {}", describer.user.name);
        CertificatorGeneratorCommand command = new CertificatorGeneratorCommand();
        Certificate certificate = certificateGenerator.generateCertificate(describer.createParticipant(), command);
        File pdfFile = certificateExporter.export(certificate);
        LOGGER.info("Certificado criado e exportado em arquivo: {} com {} bytes ", pdfFile.getAbsolutePath(), certificate.getFileContent().length);
    }
}
