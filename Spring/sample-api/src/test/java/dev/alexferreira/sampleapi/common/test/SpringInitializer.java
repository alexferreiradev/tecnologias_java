package dev.alexferreira.sampleapi.common.test;

import dev.alexferreira.sampleapi.common.container.ConfigurableContainer;
import dev.alexferreira.sampleapi.common.container.CustomSqlDataBaseContainer;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

   private static final ConfigurableContainer sqlContainer = new CustomSqlDataBaseContainer();

   @Override
   public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
      sqlContainer.configure(applicationContext);
   }
}
