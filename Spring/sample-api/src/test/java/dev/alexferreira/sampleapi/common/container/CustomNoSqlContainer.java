package dev.alexferreira.sampleapi.common.container;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

public class CustomNoSqlContainer extends MongoDBContainer
   implements InitializerConfigurableContainer, DynamicPropertyConfigurableContainer {

   public CustomNoSqlContainer() {
      super(DockerImageName.parse("mongo:5.0.17"));
      withEnv("MONGO_INITDB_DATABASE", "sample_api");
      setHostAccessible(true);

      start();
   }

   @Override
   public void configure(ConfigurableApplicationContext applicationContext) {
      TestPropertyValues.of(Map.of("spring.data.mongodb.uri", createUrl())).applyTo(applicationContext);
   }

   @Override
   public void configure(DynamicPropertyRegistry registry) {
      registry.add("spring.data.mongodb.uri", this::createUrl);
   }

   private String createUrl() {
      return getReplicaSetUrl("sample_api");
   }
}
