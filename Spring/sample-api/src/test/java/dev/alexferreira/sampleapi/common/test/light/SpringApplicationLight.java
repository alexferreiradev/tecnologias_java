package dev.alexferreira.sampleapi.common.test.light;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"dev.alexferreira.sampleapi"})
public class SpringApplicationLight {

   public static void main(String[] args) {
      SpringApplication.run(SpringApplicationLight.class, args);
   }
}
