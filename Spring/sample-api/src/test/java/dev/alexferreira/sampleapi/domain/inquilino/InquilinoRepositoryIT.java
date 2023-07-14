package dev.alexferreira.sampleapi.domain.inquilino;

import dev.alexferreira.sampleapi.common.fixture.DomainFixtures;
import dev.alexferreira.sampleapi.common.test.BaseRepositoryIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InquilinoRepositoryIT extends BaseRepositoryIT {

   @Autowired InquilinoRepository repository;

   @Test
   void shouldSaveInquilino() {
      Inquilino inquilinoSaved = repository.save(DomainFixtures.createInquilino());

      Inquilino inquilino = repository.findById(inquilinoSaved.getId()).get();
      assertNotNull(inquilino);
   }
}
