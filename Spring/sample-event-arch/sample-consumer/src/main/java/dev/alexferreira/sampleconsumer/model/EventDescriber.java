package dev.alexferreira.sampleconsumer.model;

import dev.alexferreira.sampleconsumer.module.certificate.model.Event;

import java.util.Date;
import java.util.List;

public class EventDescriber {
    public String name;
    public String data;
    public List<User> participants;

    public Event createEvent() {
        Event event = new Event();
        event.setName(name);
        event.setExecutor(name);
        event.setDateStarted(new Date());
        event.setTalkerTopics("não informado");

        return event;
    }
}
