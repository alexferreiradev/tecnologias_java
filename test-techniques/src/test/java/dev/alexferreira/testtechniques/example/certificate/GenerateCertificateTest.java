package dev.alexferreira.testtechniques.example.certificate;

import dev.alexferreira.testtechniques.example.certificate.model.Certificate;
import dev.alexferreira.testtechniques.example.certificate.model.CertificateData;
import dev.alexferreira.testtechniques.example.certificate.service.ExportService;
import org.easymock.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(EasyMockExtension.class)
class GenerateCertificateTest extends EasyMockSupport {

    @Mock
    private ExportService exportService;

    @TestSubject()
    private GenerateCertificate generateCertificate = new GenerateCertificate(null);

    @Test
    void generateCertificate() {
        exportService.exportPDFFile(EasyMock.anyObject(Certificate.class));
        replayAll();

        CertificateData certificateData = new CertificateData();
        generateCertificate.generateCertificate(certificateData);

        verifyAll();
    }
}
