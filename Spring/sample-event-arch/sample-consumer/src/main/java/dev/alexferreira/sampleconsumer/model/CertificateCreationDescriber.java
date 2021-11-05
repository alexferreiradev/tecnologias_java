package dev.alexferreira.sampleconsumer.model;

import dev.alexferreira.sampleconsumer.module.certificate.model.Participant;

public class CertificateCreationDescriber {
    public User user;
    public EventDescriber eventDescriber;

    public CertificateCreationDescriber() {
    }

    public CertificateCreationDescriber(User user, EventDescriber eventDescriber) {
        this.user = user;
        this.eventDescriber = eventDescriber;
    }

    public Participant createParticipant() {
        Participant participant = new Participant();
        participant.setName(user.name);
        participant.setCpf(user.identifier);
        participant.setEvent(eventDescriber.createEvent());
        participant.setHour("4h");

        return participant;
    }
}
