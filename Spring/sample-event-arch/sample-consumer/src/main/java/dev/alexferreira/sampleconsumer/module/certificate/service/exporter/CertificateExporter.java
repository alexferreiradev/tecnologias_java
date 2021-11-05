package dev.alexferreira.sampleconsumer.module.certificate.service.exporter;

import dev.alexferreira.sampleconsumer.module.certificate.model.Certificate;

import java.io.File;
import java.util.List;

/**
 * Interface utilizada para exportar certificados.
 *
 * @see dev.alexferreira.sampleconsumer.module.certificate.service.exporter.PdfExporter
 */
public interface CertificateExporter {

    /**
     * Exporta a lista de certificados para determinado tipo de acordo com implementação.
     *
     * @param certificates lista de certificados a serem exportados.
     * @return lista de arquivos pdf gerados.
     */
    List<File> export(List<Certificate> certificates);

    File export(Certificate certificate);
}
