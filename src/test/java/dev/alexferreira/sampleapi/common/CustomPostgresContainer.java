package dev.alexferreira.sampleapi.common;

import org.springframework.boot.test.util.*;
import org.springframework.context.*;
import org.testcontainers.containers.*;
import org.testcontainers.utility.*;

public class CustomPostgresContainer extends PostgreSQLContainer<CustomPostgresContainer> implements InjectedContainer {

   public CustomPostgresContainer() {
      super(DockerImageName.parse("postgres:13-alpine").asCompatibleSubstituteFor("postgres"));
      withUsername("postgres");
      withPassword("postgres");
      withDatabaseName("open-certificate");
      withExposedPorts(5432);
      withExposedPorts(5432);
   }

   @Override public void configureProperties(ConfigurableApplicationContext applicationContext) {
      TestPropertyValues.of("spring.datasource.url="+this.getJdbcUrl()).applyTo(applicationContext);
   }
}
