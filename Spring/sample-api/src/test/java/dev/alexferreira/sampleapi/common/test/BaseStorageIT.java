package dev.alexferreira.sampleapi.common.test;

import dev.alexferreira.sampleapi.common.container.CustomStorageContainer;
import dev.alexferreira.sampleapi.common.container.DynamicPropertyConfigurableContainer;
import dev.alexferreira.sampleapi.configuration.LoggerBeanFactory;
import dev.alexferreira.sampleapi.configuration.storage.AzureConfiguration;
import dev.alexferreira.sampleapi.configuration.storage.AzureStorageProperties;
import dev.alexferreira.sampleapi.infrastructure.storage.AzureStorage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
		AzureConfiguration.class,
		AzureStorage.class,
		AzureStorageProperties.class,
		LoggerBeanFactory.class
}, loader = AnnotationConfigContextLoader.class)
public class BaseStorageIT {

	private static final DynamicPropertyConfigurableContainer storage = new CustomStorageContainer();

	@DynamicPropertySource
	static void datasourceProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		storage.configure(dynamicPropertyRegistry);
	}

}
