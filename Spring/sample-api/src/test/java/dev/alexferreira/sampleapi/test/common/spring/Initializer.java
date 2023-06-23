package dev.alexferreira.sampleapi.test.common.spring;

import dev.alexferreira.sampleapi.test.common.container.*;
import org.springframework.boot.test.util.*;
import org.springframework.context.*;

import java.util.*;

public class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

   private Configurator configurator = new PostgresContainer();
   private Configurator noSqlConfigurator = new MongoCustomContainer();

   @Override
   public void initialize(ConfigurableApplicationContext applicationContext) {
      Map<String, String> newProperties = configurator.createDynamicProperties();
      Map<String, String> noSqlProperties = noSqlConfigurator.createDynamicProperties();
      Map<String, String> map = new HashMap<>();
      map.putAll(newProperties);
      map.putAll(noSqlProperties);

      TestPropertyValues.of(map).applyTo(applicationContext);
   }

   public interface Configurator {
      Map<String, String> createDynamicProperties();
   }
}
