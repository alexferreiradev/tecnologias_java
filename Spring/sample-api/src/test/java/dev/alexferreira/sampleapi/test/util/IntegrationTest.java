package dev.alexferreira.sampleapi.test.util;

import dev.alexferreira.sampleapi.test.container.*;
import dev.alexferreira.sampleapi.test.spring.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;

@SpringBootTest
@ActiveProfiles("test")
public abstract class IntegrationTest {

   private static final Initializer.Configurator configurator = new PostgresContainer();

   @DynamicPropertySource
   public static void configureDynamicProperties(DynamicPropertyRegistry registry) {
      System.out.println("dynamic prop");
      configurator.createDynamicProperties().forEach((key, value) -> registry.add(key, () -> value));
   }
}
