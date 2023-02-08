package dev.alexferreira.sampleapi;

import dev.alexferreira.sampleapi.modules.product.model.*;
import dev.alexferreira.sampleapi.modules.product.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.core.env.*;
import org.springframework.test.context.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest @ActiveProfiles("dev") class SampleApiApplicationTest {

   @Autowired Environment environment;

   @Autowired ProductRepository repository;

   @Test void main() {
      assertNotNull(environment);

      Product entity = new Product();
      entity.setName("teste");
      repository.save(entity);
      assertNotNull(repository.findAll().iterator().next());
   }
}
