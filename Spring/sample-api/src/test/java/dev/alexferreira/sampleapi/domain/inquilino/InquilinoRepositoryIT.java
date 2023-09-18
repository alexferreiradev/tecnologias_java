package dev.alexferreira.sampleapi.domain.inquilino;

import dev.alexferreira.sampleapi.common.fixture.DomainFixtures;
import dev.alexferreira.sampleapi.common.test.BaseRepositoryIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InquilinoRepositoryIT extends BaseRepositoryIT {

   @Autowired InquilinoRepository repository;

   @Test
   void shouldSaveInquilino() {
      Inquilino inquilinoSaved = repository.save(DomainFixtures.createInquilino());

      Inquilino inquilino = repository.findById(inquilinoSaved.getId()).get();
      assertNotNull(inquilino);
   }

   @Test
   void shouldThrow_whenSaveInquilinoWithSameDocument() {
      Inquilino inquilinoSaved = repository.saveAndFlush(DomainFixtures.createInquilino());
      Inquilino inquilinoNew = DomainFixtures.createInquilino();
      inquilinoNew.setDocumento(inquilinoSaved.getDocumento());

      DataIntegrityViolationException violationException =
         assertThrows(DataIntegrityViolationException.class, () -> repository.saveAndFlush(inquilinoNew));

      String expectedExceptionMsg = String.format("Key (documento)=(%s) already exists", inquilinoNew.getDocumento());
      assertTrue(violationException.getMostSpecificCause().getMessage().contains(expectedExceptionMsg));

      Inquilino inquilino = repository.findById(inquilinoSaved.getId()).get();
      assertNotNull(inquilino);
   }
}
