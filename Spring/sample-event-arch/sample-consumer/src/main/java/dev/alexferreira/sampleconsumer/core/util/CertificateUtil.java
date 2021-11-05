package dev.alexferreira.sampleconsumer.core.util;

import dev.alexferreira.sampleconsumer.module.certificate.model.Certificate;

public final class CertificateUtil {

    /**
     * Cria um nome para arquivo de certificado com o padrão: nome.extensao.
     *
     * @param certificate certificado
     * @return nome criado.
     */
    public static String createFileName(Certificate certificate) {
        if (certificate == null) {
            throw new IllegalArgumentException("Certificado não é válido, não possue nome de arquivo ou extensão");
        }

        return String.format("%s.%s", certificate.getFileName(), certificate.getFileExtension());
    }
}
