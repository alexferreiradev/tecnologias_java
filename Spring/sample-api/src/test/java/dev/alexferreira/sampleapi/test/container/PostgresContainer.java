package dev.alexferreira.sampleapi.test.container;

import dev.alexferreira.sampleapi.test.spring.*;
import org.testcontainers.containers.*;
import org.testcontainers.utility.*;

import java.util.*;

public class PostgresContainer extends PostgreSQLContainer<PostgresContainer> implements Initializer.Configurator {

   public PostgresContainer() {
      super(DockerImageName.parse("postgres"));

      withUsername("postgres");
      withPassword("postgres");
      withDatabaseName("sample_api");

      start();
   }

   @Override
   public Map<String, String> createDynamicProperties() {
      return Map.of("spring.datasource.url", getJdbcUrl());
   }
}
