package dev.alexferreira.sampleapi.infrastructure.storage;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import dev.alexferreira.sampleapi.domain.inquilino.ImagemInquilinoStorage;
import dev.alexferreira.sampleapi.domain.inquilino.Inquilino;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AzureStorage implements ImagemInquilinoStorage {

   @Autowired BlobContainerClient blobContainerClient;

   @Autowired Logger logger;

   @Override
   public String save(Inquilino inquilino, byte[] image) {
      BlobClient blobClient = blobContainerClient.getBlobClient(createPath(inquilino));
      byte[] bytes = Base64.getEncoder().encode(image);
      blobClient.upload(BinaryData.fromBytes(bytes), false);

      return blobClient.getBlobName();
   }

   @Override
   public byte[] get(Inquilino inquilino) {
      BlobClient blobClient = blobContainerClient.getBlobClient(createPath(inquilino));
      byte[] bytes = blobClient.downloadContent().toBytes();

      return Base64.getDecoder().decode(bytes);
   }

   private String completePath(String path) {
      return String.format("%s/%s", blobContainerClient.getBlobContainerUrl(), path);
   }

   private String createPath(Inquilino inquilino) {
      return String.format("inquilino-image/%s.%s", inquilino.getId(), "png");
   }
}
