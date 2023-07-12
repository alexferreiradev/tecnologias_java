package dev.alexferreira.sampleapi.common.container;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

public class CustomStorageContainer extends GenericContainer<CustomStorageContainer>
   implements InitializerConfigurableContainer, DynamicPropertyConfigurableContainer {

   public CustomStorageContainer() {
      super(DockerImageName.parse("mcr.microsoft.com/azure-storage/azurite:3.17.1"));

      addExposedPorts(10000, 10001, 10002);

      start();
   }

   @Override
   public void configure(ConfigurableApplicationContext applicationContext) {
      TestPropertyValues.of(Map.of("storage.connection-string", buildUrl())).applyTo(applicationContext);
   }

   private String buildUrl() {
      return String.format(
         "DefaultEndpointsProtocol=http;AccountName=devstoreaccount1;"
            + "AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;"
            + "BlobEndpoint=http://127.0.0.1:%s/devstoreaccount1;QueueEndpoint=http://127.0.0.1:%s/devstoreaccount1;"
            + "TableEndpoint=http://127.0.0.1:%s/devstoreaccount1;",
         getMappedPort(10000),
         getMappedPort(10001),
         getMappedPort(10002)
      );
   }

   @Override
   public void configure(DynamicPropertyRegistry registry) {
      registry.add("storage.connection-string", this::buildUrl);
      registry.add("storage.container-name", () -> "sample-api");
   }
}
