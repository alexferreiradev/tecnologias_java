package dev.alexferreira;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
public class HealthCheckResource implements HealthCheck {

	@Override
	public HealthCheckResponse call() {
		return HealthCheckResponse.up("GreetingResource");
	}
}
