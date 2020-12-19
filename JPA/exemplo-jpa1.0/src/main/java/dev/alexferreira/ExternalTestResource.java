package dev.alexferreira;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/external-service")
@Produces(MediaType.TEXT_PLAIN)
public class ExternalTestResource {

	@GET
	public Response get() {
		return Response.ok("Resposta de serviço externo").build();
	}

}
