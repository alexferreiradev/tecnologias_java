package dev.alexferreira.sampleapi.common.test;

import dev.alexferreira.sampleapi.usecase.CreateAuthorization;
import dev.alexferreira.sampleapi.usecase.RegisterUser;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationMockBean {

	@Bean
	public RegisterUser register() {
		return Mockito.mock();
	}

	@Bean
	public CreateAuthorization create() {
		return Mockito.mock();
	}
}
