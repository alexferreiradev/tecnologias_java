package dev.alexferreira.sampleapi.common.container;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

public class CustomSqlDataBaseContainer extends GenericContainer<CustomSqlDataBaseContainer>
   implements InitializerConfigurableContainer, DynamicPropertyConfigurableContainer {

   public CustomSqlDataBaseContainer() {
      super(DockerImageName.parse("debezium/postgres:14-alpine").asCompatibleSubstituteFor("postgres"));
      addEnv("POSTGRES_DB", "sample-api");
      addEnv("POSTGRES_USER", "postgres");
      addEnv("POSTGRES_PASSWORD", "postgres");
      addExposedPort(5432);

      start();
   }

   @Override
   public void configure(ConfigurableApplicationContext applicationContext) {
      String dataBaseUrl = createDataBaseUrl();

      TestPropertyValues.of(Map.of("spring.datasource.url", dataBaseUrl)).applyTo(applicationContext);
   }

   private String createDataBaseUrl() {
      String port = String.valueOf(getFirstMappedPort());
      String dataBaseUrl = String.format("jdbc:postgresql://localhost:%s/sample-api", port);
      return dataBaseUrl;
   }

   @Override
   public void configure(DynamicPropertyRegistry registry) {
      registry.add("spring.datasource.url", this::createDataBaseUrl);
   }
}
