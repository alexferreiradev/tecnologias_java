package dev.alexferreira.sampleapi.test.spring;

import dev.alexferreira.sampleapi.test.container.*;
import org.springframework.boot.test.util.*;
import org.springframework.context.*;

import java.util.*;

public class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

   private final Configurator configurator = new PostgresContainer();

   @Override
   public void initialize(ConfigurableApplicationContext applicationContext) {
      Map<String, String> newProperties = configurator.createDynamicProperties();
      TestPropertyValues.of(newProperties).applyTo(applicationContext);
   }

   public interface Configurator {
      Map<String, String> createDynamicProperties();
   }
}
