package dev.alexferreira.sampleapi.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

@Configuration
@Profile("!dev")
public class LoggerBeanFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerBeanFactory.class);

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Logger createLogger(InjectionPoint injectionPoint) {
		Logger logger = LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
		LOGGER.trace("Log injection configured to {}", injectionPoint.getMember().getDeclaringClass().getName());

		return logger;
	}
}
