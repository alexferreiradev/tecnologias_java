package dev.alexferreira.sampleapi.configuration.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "storage", ignoreUnknownFields = false)
public class AzureStorageProperties {
   private String connectionString;
   private String containerName;

   public String getConnectionString() {
      return connectionString;
   }

   public void setConnectionString(String connectionString) {
      this.connectionString = connectionString;
   }

   public String getContainerName() {
      return containerName;
   }

   public void setContainerName(String containerName) {
      this.containerName = containerName;
   }
}
