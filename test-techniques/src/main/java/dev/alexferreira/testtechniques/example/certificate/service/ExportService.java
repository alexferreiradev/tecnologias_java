package dev.alexferreira.testtechniques.example.certificate.service;

import dev.alexferreira.testtechniques.example.certificate.model.Certificate;

public interface ExportService {

    void exportJsonFile(Certificate certificate);

    void exportPDFFile(Certificate certificate);

}
