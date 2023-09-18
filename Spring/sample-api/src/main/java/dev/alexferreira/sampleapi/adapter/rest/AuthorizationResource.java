package dev.alexferreira.sampleapi.adapter.rest;

import dev.alexferreira.sampleapi.adapter.rest.request.CreateAuthorizationRequest;
import dev.alexferreira.sampleapi.adapter.rest.response.AuthorizationResponse;
import dev.alexferreira.sampleapi.usecase.CreateAuthorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authorizations", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorizationResource {

   private final CreateAuthorization createAuthorization;

   public AuthorizationResource(CreateAuthorization createAuthorization) {
      this.createAuthorization = createAuthorization;
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public AuthorizationResponse createAuthorization(@RequestBody CreateAuthorizationRequest request) {
      String authorizationId = createAuthorization.execute(request.toInput());

      return new AuthorizationResponse(authorizationId);
   }

}
