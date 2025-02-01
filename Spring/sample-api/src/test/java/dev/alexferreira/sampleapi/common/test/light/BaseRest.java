package dev.alexferreira.sampleapi.common.test.light;

import dev.alexferreira.sampleapi.adapter.rest.ResourceExceptionHandler;
import dev.alexferreira.sampleapi.configuration.JsonConverterConfiguration;
import dev.alexferreira.sampleapi.configuration.LoggerBeanFactory;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {SpringApplicationLight.class})
@Import({JsonConverterConfiguration.class, LoggerBeanFactory.class, ResourceExceptionHandler.class})
public abstract class BaseRest {

}
