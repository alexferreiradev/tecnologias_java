package dev.alexferreira.sampleapi.test.util;

import dev.alexferreira.sampleapi.test.container.*;
import dev.alexferreira.sampleapi.test.spring.*;
import org.springframework.boot.autoconfigure.data.jpa.*;
import org.springframework.boot.test.autoconfigure.data.mongo.*;
import org.springframework.test.context.*;

@ActiveProfiles("test")
@DataMongoTest
@ContextConfiguration(classes = JpaRepositoriesAutoConfiguration.class, initializers = Initializer.class)
public abstract class IntegrationTest {

   private static final Initializer.Configurator configurator = new PostgresContainer();

   @DynamicPropertySource
   public static void configureDynamicProperties(DynamicPropertyRegistry registry) {
      System.out.println("dynamic prop");
      configurator.createDynamicProperties().forEach((key, value) -> registry.add(key, () -> value));
   }
}
