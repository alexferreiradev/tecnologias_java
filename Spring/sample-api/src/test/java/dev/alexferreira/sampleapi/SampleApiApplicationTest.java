package dev.alexferreira.sampleapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("dev")
class SampleApiApplicationTest {

   @Autowired Environment environment;

   @Test
   void main() {
      assertNotNull(environment);
      assertNotNull(environment.getProperty("spring.profiles.active"));
   }
}
