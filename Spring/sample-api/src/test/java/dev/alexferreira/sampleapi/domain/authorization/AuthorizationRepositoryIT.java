package dev.alexferreira.sampleapi.domain.authorization;

import dev.alexferreira.sampleapi.common.fixture.DomainFixtures;
import dev.alexferreira.sampleapi.common.test.BaseRepositoryIT;
import dev.alexferreira.sampleapi.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AuthorizationRepositoryIT extends BaseRepositoryIT {

   @Autowired AuthorizationRepository repository;

   private final Authorization authorization = DomainFixtures.createAuthorization();

   @Test
   void shouldSaveAuthorization() {
      Authorization savedAuth = repository.save(authorization);

      assertNotNull(repository.findById(savedAuth.id).orElseThrow());
   }
}
