package dev.alexferreira.sampleapi.common.test.light;

import dev.alexferreira.sampleapi.adapter.rest.ResourceExceptionHandler;
import dev.alexferreira.sampleapi.configuration.LoggerBeanFactory;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {SpringApplicationLight.class})
@Import({JacksonAutoConfiguration.class, LoggerBeanFactory.class, ResourceExceptionHandler.class})
public abstract class BaseRest {

}
