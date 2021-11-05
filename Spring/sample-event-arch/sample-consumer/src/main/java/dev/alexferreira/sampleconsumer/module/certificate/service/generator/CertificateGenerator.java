package dev.alexferreira.sampleconsumer.module.certificate.service.generator;

import dev.alexferreira.sampleconsumer.module.certificate.command.CertificatorGeneratorCommand;
import dev.alexferreira.sampleconsumer.module.certificate.model.Certificate;
import dev.alexferreira.sampleconsumer.module.certificate.model.Participant;

import java.util.List;

/**
 * Interface para definir geradores de certificados.
 *
 * @see GoJavaGenerator
 */
public interface CertificateGenerator {

    List<Certificate> generateCertificates(CertificatorGeneratorCommand command);

    Certificate generateCertificate(Participant participant, CertificatorGeneratorCommand command);

}
