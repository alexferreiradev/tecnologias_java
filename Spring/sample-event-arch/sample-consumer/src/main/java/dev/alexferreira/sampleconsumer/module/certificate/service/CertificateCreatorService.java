package dev.alexferreira.sampleconsumer.module.certificate.service;

import dev.alexferreira.sampleconsumer.model.CertificateCreationDescriber;

public interface CertificateCreatorService {
    void createPDF(CertificateCreationDescriber describer);
}
