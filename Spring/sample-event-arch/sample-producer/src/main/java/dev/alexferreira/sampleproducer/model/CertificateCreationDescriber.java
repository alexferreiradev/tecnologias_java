package dev.alexferreira.sampleproducer.model;

public class CertificateCreationDescriber {
    public User user;
    public EventDescriber eventDescriber;

    public CertificateCreationDescriber() {
    }

    public CertificateCreationDescriber(User user, EventDescriber eventDescriber) {
        this.user = user;
        this.eventDescriber = eventDescriber;
    }
}
