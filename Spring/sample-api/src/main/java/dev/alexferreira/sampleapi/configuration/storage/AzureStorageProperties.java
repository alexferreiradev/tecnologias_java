package dev.alexferreira.sampleapi.configuration.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@ConfigurationProperties(prefix = "storage", ignoreUnknownFields = false)
@PropertySource("classpath:application.properties")
public class AzureStorageProperties {

	@Value("${storage.connection-string}")
	private String connectionString;

	@Value("${storage.container-name}")
	private String containerName;

	public String getConnectionString() {
		return connectionString;
	}

	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

	public String getContainerName() {
		return containerName;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}
}
