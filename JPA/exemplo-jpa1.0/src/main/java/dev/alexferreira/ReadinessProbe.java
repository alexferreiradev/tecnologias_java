package dev.alexferreira;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;

@Readiness
public class ReadinessProbe implements HealthCheck {

	@Inject
	@RestClient
	ExternalResourceDefinition externalResource;

	@Override
	public HealthCheckResponse call() {
		if (externalResource.get() == null) {
			return HealthCheckResponse.down("External service");
		} else {
			return HealthCheckResponse.up("External service");
		}
	}
}
