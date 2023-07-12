package dev.alexferreira.sampleapi.common.container;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.test.context.DynamicPropertyRegistry;

public interface InitializerConfigurableContainer {

   void configure(ConfigurableApplicationContext applicationContext);
}
