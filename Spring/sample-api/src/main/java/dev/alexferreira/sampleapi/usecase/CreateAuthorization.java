package dev.alexferreira.sampleapi.usecase;

import dev.alexferreira.sampleapi.domain.authorization.Authorization;
import dev.alexferreira.sampleapi.domain.authorization.AuthorizationRepository;
import dev.alexferreira.sampleapi.domain.authorization.TenantAuthorized;
import dev.alexferreira.sampleapi.domain.authorization.exception.UserNotFoundException;
import dev.alexferreira.sampleapi.domain.tenant.Tenant;
import dev.alexferreira.sampleapi.domain.tenant.TenantRepository;
import dev.alexferreira.sampleapi.usecase.input.CreateAuthorizationInput;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CreateAuthorization {

   private final TenantRepository tenantRepository;
   private final AuthorizationRepository authorizationRepository;
   private final Logger logger;

   public CreateAuthorization(
           TenantRepository tenantRepository,
           AuthorizationRepository authorizationRepository,
           Logger logger
   ) {
      this.tenantRepository = tenantRepository;
      this.authorizationRepository = authorizationRepository;
      this.logger = logger;
   }

   public String execute(CreateAuthorizationInput input) {
      logger.debug("Creating authorization for document: {}", input.document);

      Tenant tenant = tenantRepository.findByDocument(input.document).orElseThrow(UserNotFoundException::new);

      Authorization authorization = createAuthorization(input, tenant);
      logger.info("Authorization({}) created for document: {}",authorization.id, input.document);

      return authorization.id;
   }

   private Authorization createAuthorization(CreateAuthorizationInput input, Tenant tenant) {
      Authorization authorization = new Authorization();
      TenantAuthorized tenantAuthorized = new TenantAuthorized();
      tenantAuthorized.id = tenant.getId().toString();
      tenantAuthorized.document = tenant.getDocument();
      authorization.tenantAuthorized = tenantAuthorized;
      authorization.createdAt = Instant.now();
      authorization.doorType = input.indoorType;
      authorization.doorDescription = input.indoorDescription;

      return authorizationRepository.save(authorization);
   }
}
