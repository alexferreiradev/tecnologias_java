package dev.alexferreira.sampleapi.usecase;

import dev.alexferreira.sampleapi.domain.authorization.Authorization;
import dev.alexferreira.sampleapi.domain.authorization.AuthorizationRepository;
import dev.alexferreira.sampleapi.domain.authorization.exception.UserNotFoundException;
import dev.alexferreira.sampleapi.domain.user.User;
import dev.alexferreira.sampleapi.domain.user.UserRepository;
import dev.alexferreira.sampleapi.usecase.input.CreateAuthorizationInput;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CreateAuthorization {

   private final UserRepository userRepository;
   private final AuthorizationRepository authorizationRepository;
   private final Logger logger;

   public CreateAuthorization(
      UserRepository userRepository, AuthorizationRepository authorizationRepository, Logger logger
   ) {
      this.userRepository = userRepository;
      this.authorizationRepository = authorizationRepository;
      this.logger = logger;
   }

   public String execute(CreateAuthorizationInput input) {
      logger.debug("Creating authorization for document: {}", input.document);

      User user = userRepository.findByDocument(input.document).orElseThrow(UserNotFoundException::new);

      Authorization authorization = createAuthorization(input, user);
      logger.info("Authorization({}) created for document: {}",authorization.id, input.document);

      return authorization.id;
   }

   private Authorization createAuthorization(CreateAuthorizationInput input, User user) {
      Authorization authorization = new Authorization();
      authorization.userAuthorized = user;
      authorization.createdAt = Instant.now();
      authorization.tipoPorta = input.indoorType;
      authorization.descPorta = input.indoorDescription;

      return authorizationRepository.save(authorization);
   }
}
