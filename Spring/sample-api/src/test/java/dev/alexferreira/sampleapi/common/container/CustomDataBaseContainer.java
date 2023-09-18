package dev.alexferreira.sampleapi.common.container;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

public class CustomDataBaseContainer extends GenericContainer<CustomDataBaseContainer>
   implements InitializerConfigurableContainer, DynamicPropertyConfigurableContainer {

   public CustomDataBaseContainer() {
      super(DockerImageName.parse("debezium/postgres:14-alpine").asCompatibleSubstituteFor("postgres"));
      addEnv("POSTGRES_DB", "sample_api");
      addEnv("POSTGRES_USER", "sample_api");
      addEnv("POSTGRES_PASSWORD", "sample_api");
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
      String dataBaseUrl = String.format("jdbc:postgresql://localhost:%s/sample_api", port);
      return dataBaseUrl;
   }

   @Override
   public void configure(DynamicPropertyRegistry registry) {
      registry.add("spring.datasource.url", this::createDataBaseUrl);
   }
}
