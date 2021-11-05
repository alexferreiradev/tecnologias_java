package dev.alexferreira.sampleconsumer.module.certificate.service.generator.token;

import dev.alexferreira.sampleconsumer.module.certificate.model.Certificate;

/**
 * Interface que define geradores de token para certificados.
 *
 * @see TokenGenerator_SHA256
 */
public interface TokenGenerator {

    String generateToken(Certificate certificate);

}
