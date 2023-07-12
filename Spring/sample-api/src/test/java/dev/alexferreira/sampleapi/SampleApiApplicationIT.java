package dev.alexferreira.sampleapi;

import dev.alexferreira.sampleapi.common.test.BaseIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SampleApiApplicationIT extends BaseIT {

   @Autowired Environment environment;

   @Test
   void main() {
      assertNotNull(environment);
      assertNotNull(environment.getProperty("spring.profiles.active"));
   }
}
