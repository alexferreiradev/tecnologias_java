package dev.alexferreira.sampleapi.common.test.light;

import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {SpringApplicationLight.class})
@Import(JacksonAutoConfiguration.class)
public abstract class BaseRest {

}
