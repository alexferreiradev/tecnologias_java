package dev.alexferreira.testtechniques.example.certificate.model;

public class Certificate {

    public String fileName;
    public String student;
    public String date;

    public static Certificate from(CertificateData certificateData) {
        Certificate certificate = new Certificate();

        return certificate;
    }
}
