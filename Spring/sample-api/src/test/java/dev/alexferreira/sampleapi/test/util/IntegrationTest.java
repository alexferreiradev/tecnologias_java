package dev.alexferreira.sampleapi.test.util;

import dev.alexferreira.sampleapi.test.container.*;
import dev.alexferreira.sampleapi.test.spring.*;
import org.springframework.boot.test.autoconfigure.data.mongo.*;
import org.springframework.test.context.*;
import org.testcontainers.containers.*;
import org.testcontainers.utility.*;

@ActiveProfiles("test")
@DataMongoTest
@ContextConfiguration(initializers = Initializer.class)
public abstract class IntegrationTest {

   private static final Initializer.Configurator dbConfigurator = new PostgresContainer();
   private static final GenericContainer noSqlDbConfigurator = new GenericContainer(DockerImageName.parse("mongo"));

   @DynamicPropertySource
   public static void configureDynamicProperties(DynamicPropertyRegistry registry) {
      System.out.println("dynamic prop");
      dbConfigurator.createDynamicProperties().forEach((key, value) -> registry.add(key, () -> value));
      dbConfigurator.createDynamicProperties().forEach((key, value) -> registry.add(key, () -> value));
   }
}
