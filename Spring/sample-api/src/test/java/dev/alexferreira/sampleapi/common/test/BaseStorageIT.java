package dev.alexferreira.sampleapi.common.test;

import dev.alexferreira.sampleapi.common.container.CustomStorageContainer;
import dev.alexferreira.sampleapi.common.container.DynamicPropertyConfigurableContainer;
import dev.alexferreira.sampleapi.configuration.LoggerBeanFactory;
import dev.alexferreira.sampleapi.configuration.storage.AzureConfiguration;
import dev.alexferreira.sampleapi.configuration.storage.AzureStorageProperties;
import dev.alexferreira.sampleapi.infrastructure.storage.AzureStorage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("dev")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AzureConfiguration.class, AzureStorage.class, AzureStorageProperties.class})
@EnableConfigurationProperties
@Import({LoggerBeanFactory.class})
public class BaseStorageIT {

   private static final DynamicPropertyConfigurableContainer storage = new CustomStorageContainer();

   @DynamicPropertySource
   static void datasourceProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
      storage.configure(dynamicPropertyRegistry);
   }

}
