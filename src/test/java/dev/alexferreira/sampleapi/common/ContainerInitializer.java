package dev.alexferreira.sampleapi.common;

import org.springframework.context.*;


public class ContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

   private CustomPostgresContainer CUSTOM_POSTGRES_CONTAINER = new CustomPostgresContainer();

//   static {
//      CUSTOM_POSTGRES_CONTAINER.start();
//   }

   @Override public void initialize(ConfigurableApplicationContext applicationContext) {
      CUSTOM_POSTGRES_CONTAINER.start();
      CUSTOM_POSTGRES_CONTAINER.configureProperties(applicationContext);
   }
}
