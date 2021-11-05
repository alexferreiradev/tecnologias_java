package dev.alexferreira.sampleconsumer.module.certificate.service.exporter;

import dev.alexferreira.sampleconsumer.core.helper.FileHelper;
import dev.alexferreira.sampleconsumer.core.util.CertificateUtil;
import dev.alexferreira.sampleconsumer.module.certificate.model.Certificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfExporter implements CertificateExporter {

    private static final String CERT_DIR_NAME = "certificados";

    private final Logger logger;

    public PdfExporter() {
        this.logger = LoggerFactory.getLogger(PdfExporter.class);
    }

    @Override
    public List<File> export(List<Certificate> certificates) {
        logger.info("Iniciando exportação de pdf de certificados");

        List<File> certPdfFileList = new ArrayList<>();
        for (Certificate certificate : certificates) {
            logger.debug("Exportando certificado: " + certificate.getFileName());
            File pdfFile = exportPDF(certificate);
            certPdfFileList.add(pdfFile);
            logger.debug("Certificado exportado");
        }

        logger.debug("Certificados exportados");

        return certPdfFileList;
    }

    @Override
    public File export(Certificate certificate) {
        logger.info("Iniciando exportação de pdf de certificados");
        File pdfFile = exportPDF(certificate);
        logger.debug("Certificado exportado");

        return pdfFile;
    }

    private void createExportDirectory() {
        File certDirExport = new File("./" + CERT_DIR_NAME);
        FileHelper.createDirOrThrow(certDirExport);
    }

    private File exportPDF(Certificate certificate) {
        try {
            File file = Files.createTempFile("certificadoDraft_", CertificateUtil.createFileName(certificate)).toFile();
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                createExportDirectory();
                FileHelper.createFileOrThrow(file.getAbsolutePath(), true);

                fileOutputStream.write(certificate.getFileContent());
                fileOutputStream.flush();

                return file;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro de IO ao tentar criar arquivo PDF", e);
        }
    }
}
