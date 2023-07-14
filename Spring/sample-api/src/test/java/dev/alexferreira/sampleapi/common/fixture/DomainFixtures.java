package dev.alexferreira.sampleapi.common.fixture;

import dev.alexferreira.sampleapi.domain.inquilino.Inquilino;

public interface DomainFixtures {

   static Inquilino createInquilino() {
      Inquilino inquilino = new Inquilino();
      inquilino.setNome("Fake Name");
      inquilino.setApartamento("101");
      inquilino.setBloco("A");
      inquilino.setDocumento("123456789");
      inquilino.setImagePath("path/to/image");

      return inquilino;
   }
}
