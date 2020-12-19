package dev.alexferreira;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@RegisterRestClient(baseUri = "http://localhost:8080/external-service")
public interface ExternalResourceDefinition {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	String get();
}
