package dev.alexferreira.testtechniques.example.certificate;

import dev.alexferreira.testtechniques.example.certificate.model.Certificate;
import dev.alexferreira.testtechniques.example.certificate.model.CertificateData;
import dev.alexferreira.testtechniques.example.certificate.service.ExportService;

public class GenerateCertificate {

    private ExportService exportService;

    public GenerateCertificate(ExportService exportService) {
        this.exportService = exportService;
    }

    public void generateCertificate(CertificateData certificateData) {

        exportService.exportPDFFile(Certificate.from(certificateData));
    }
}
