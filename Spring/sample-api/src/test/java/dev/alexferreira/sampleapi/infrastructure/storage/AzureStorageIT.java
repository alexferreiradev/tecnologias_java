package dev.alexferreira.sampleapi.infrastructure.storage;

import dev.alexferreira.sampleapi.common.fixture.DomainFixtures;
import dev.alexferreira.sampleapi.common.test.BaseStorageIT;
import dev.alexferreira.sampleapi.domain.inquilino.ImagemInquilinoStorage;
import dev.alexferreira.sampleapi.domain.inquilino.Inquilino;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AzureStorageIT extends BaseStorageIT {

   @Autowired ImagemInquilinoStorage azureStorage;

   private final Inquilino inquilino = DomainFixtures.createInquilino();

   @Order(0)
   @Test
   void save() {
      String path = azureStorage.save(inquilino, "teste".getBytes(StandardCharsets.UTF_8));
      assertNotNull(path);
   }

   @Test
   @Order(1)
   void get() {
      String path = azureStorage.save(inquilino, "teste".getBytes(StandardCharsets.UTF_8));
      byte[] bytes = azureStorage.get(inquilino);
      assertNotNull(bytes);
   }
}
