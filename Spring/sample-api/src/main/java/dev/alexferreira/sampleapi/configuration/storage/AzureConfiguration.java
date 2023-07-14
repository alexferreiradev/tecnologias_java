package dev.alexferreira.sampleapi.configuration.storage;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.blob.models.BlobErrorCode;
import com.azure.storage.blob.models.BlobStorageException;
import dev.alexferreira.sampleapi.infrastructure.storage.AzureStorage;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Configuration
public class AzureConfiguration {

   @Autowired AzureStorageProperties properties;
   @Autowired Logger logger;

   @Bean
   public BlobContainerClient blobContainerClient() {
      BlobContainerClient blobContainerClient = new BlobContainerClientBuilder()
         .connectionString(properties.getConnectionString())
         .containerName(URLEncoder.encode(properties.getContainerName(), StandardCharsets.UTF_8))
         .buildClient();

      createContainerIfNotExists(blobContainerClient);

      return blobContainerClient;
   }

   private void createContainerIfNotExists(BlobContainerClient blobContainerClient) {
      try {
         if (!blobContainerClient.exists()) blobContainerClient.create();
      } catch (BlobStorageException blobStorageEx) {
         if (blobStorageEx.getErrorCode().equals(BlobErrorCode.CONTAINER_ALREADY_EXISTS)) {
            String message = "failed to create container to azure blob storage, the container already exists";
            logger.warn(message, blobStorageEx);
         } else if (blobStorageEx.getErrorCode().equals(BlobErrorCode.AUTHORIZATION_FAILURE)) {
            logger.warn("failed to create container to azure blob storage, Auth failed", blobStorageEx);
         } else {
            throw new IllegalStateException(blobStorageEx.getMessage(), blobStorageEx);
         }
      }
   }
}
