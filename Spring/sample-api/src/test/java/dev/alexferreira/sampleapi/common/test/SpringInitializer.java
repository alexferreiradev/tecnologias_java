package dev.alexferreira.sampleapi.common.test;

import dev.alexferreira.sampleapi.common.container.InitializerConfigurableContainer;
import dev.alexferreira.sampleapi.common.container.CustomKafkaContainer;
import dev.alexferreira.sampleapi.common.container.CustomNoSqlDataBaseContainer;
import dev.alexferreira.sampleapi.common.container.CustomSqlDataBaseContainer;
import dev.alexferreira.sampleapi.common.container.CustomStorageContainer;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

   private static final InitializerConfigurableContainer sqlContainer = new CustomSqlDataBaseContainer();
   private static final InitializerConfigurableContainer noSqlContainer = new CustomNoSqlDataBaseContainer();
   private static final InitializerConfigurableContainer kafkaContainer = new CustomKafkaContainer();
   private static final InitializerConfigurableContainer storageContainer = new CustomStorageContainer();

   @Override
   public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
      sqlContainer.configure(applicationContext);
      noSqlContainer.configure(applicationContext);
      kafkaContainer.configure(applicationContext);
      storageContainer.configure(applicationContext);
   }
}
