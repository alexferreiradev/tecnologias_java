package dev.alexferreira.sampleapi.common.container;

import org.springframework.test.context.DynamicPropertyRegistry;

public interface DynamicPropertyConfigurableContainer {
   void configure(DynamicPropertyRegistry registry);
}
