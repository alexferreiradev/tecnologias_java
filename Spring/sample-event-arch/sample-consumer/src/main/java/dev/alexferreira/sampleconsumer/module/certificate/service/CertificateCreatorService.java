package dev.alexferreira.sampleconsumer.module.certificate.service;

import dev.alexferreira.sampleconsumer.model.CertificateCreationDescriber;

import java.io.IOException;

public interface CertificateCreatorService {
    void createPDF(CertificateCreationDescriber describer);
}
