package dev.alexferreira.testtechniques.example.certificate;

import dev.alexferreira.testtechniques.example.certificate.model.Certificate;
import dev.alexferreira.testtechniques.example.certificate.model.CertificateData;
import dev.alexferreira.testtechniques.example.certificate.service.ExportService;
import org.easymock.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(EasyMockExtension.class)
class GenerateCertificateTest {

    private final EasyMockSupport support = new EasyMockSupport();

    @Mock
    private ExportService exportService;

//    private GenerateCertificate generateCertificate;

    @TestSubject
    private GenerateCertificate generateCertificate = new GenerateCertificate(null);

    @Test
    void generateCertificate() {
        Certificate certificate = new Certificate();
        exportService.exportJsonFile(EasyMock.anyObject(Certificate.class));
        support.replayAll();

        CertificateData certificateData = new CertificateData();
        generateCertificate.generateCertificate(certificateData);

        support.verifyAll();
    }
}
