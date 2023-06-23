package dev.alexferreira.sampleapi;

import dev.alexferreira.sampleapi.modules.product.repository.*;
import dev.alexferreira.sampleapi.test.common.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;

import static org.junit.jupiter.api.Assertions.*;

class SecondSampleApiApplicationTest extends IntegrationTest {

   @Value("${spring.profiles.active}") String property;

   @Autowired ProductRepository productRepository;

   @Autowired ProdutoLogRepository logRepository;

   @Test
   void shouldGetProperty_whenSpringContextStart() {
      //      Debug here
      assertNotNull(property);
      assertEquals("dev", property);
   }

   @Test
   void shouldInjectRepository__whenSpringContextStart() {
      assertNotNull(productRepository);
   }

   @Test
   void shouldInjectNoSqlRepository__whenSpringContextStart() {
      assertNotNull(logRepository);
   }
}
