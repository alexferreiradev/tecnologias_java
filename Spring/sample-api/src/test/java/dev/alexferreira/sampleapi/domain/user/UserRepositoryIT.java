package dev.alexferreira.sampleapi.domain.user;

import dev.alexferreira.sampleapi.common.fixture.DomainFixtures;
import dev.alexferreira.sampleapi.common.test.BaseRepositoryIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import static org.junit.jupiter.api.Assertions.*;


class UserRepositoryIT extends BaseRepositoryIT {

   @Autowired
   UserRepository repository;
   private final User user = DomainFixtures.createUser();

   @Test
   void shouldSaveUser() {
      User save = repository.save(user);

      User actual = repository.findById(save.id).orElseThrow();
      assertNotNull(actual);
      assertEquals(save.id, actual.id);
      assertEquals(save.document, actual.document);
   }

   @Test
   void shouldFindUserByDocument() {
      repository.save(user);

      assertNotNull(repository.findByDocument(user.document).orElse(null));
   }

   @Test
   void shouldThrow_whenDocumentIsDuplicated() {
      repository.save(user);
      User user2 = DomainFixtures.createUser();
      user2.document = user.document;

      assertThrows(DuplicateKeyException.class, () -> repository.insert(user2));
   }

   @Test
   void shouldReturnEmpty_whenUserNotFoundByDocument() {
      User newUser = DomainFixtures.createUser();
      assertNull(repository.findByDocument(newUser.document).orElse(null));
   }
}
