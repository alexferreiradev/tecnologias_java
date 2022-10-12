package dev.alexferreira.sampleapi.common;

import org.springframework.context.*;

public interface InjectedContainer {

    void configureProperties(ConfigurableApplicationContext applicationContext);
}
