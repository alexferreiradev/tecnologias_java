package dev.alexferreira.sampleconsumer.module.certificate.service;

import dev.alexferreira.sampleconsumer.model.CertificateCreationDescriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;

@Service
public class CertificateCreatorServiceImpl implements CertificateCreatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CertificateCreatorServiceImpl.class);

    @Override
    public void createPDF(CertificateCreationDescriber describer) throws Exception {
        LOGGER.info("Criando certificado para: {}", describer.user.name);
        File teste = File.createTempFile("teste", ".txt");

        int F = 0;     // atual
        int ant = 0;   // anterior
        for (int i = 1; i <= 5_000_000; i++) {
            if (i == 1) {
                F = 1;
                ant = 0;
            } else {
                F += ant;
                ant = F - ant;
            }
            FileWriter fileWriter = new FileWriter(teste, true);
            fileWriter.write(ant);
        }
        LOGGER.info("File criado: {} ", teste.getAbsoluteFile());
        LOGGER.info("Certificado Criado: " + ant);
    }

    @Override
    public void sendPDF(CertificateCreationDescriber describer) {
        LOGGER.info("Enviando certificado para: {}", describer.user.name);
        int acc = 1;
//        for (int i = 1; i < 1000; i++) {
//            acc *= i;
//        }
        LOGGER.info("Certificado Enviado: " + acc);
    }
}
