package dev.alexferreira.sampleapi.common.fixture;

import dev.alexferreira.sampleapi.common.random.TestRandomValue;
import dev.alexferreira.sampleapi.domain.authorization.Authorization;
import dev.alexferreira.sampleapi.domain.inquilino.Inquilino;
import dev.alexferreira.sampleapi.domain.user.User;

import java.util.UUID;

public interface DomainFixtures {

   static Inquilino createInquilino() {
      Inquilino inquilino = new Inquilino();
      inquilino.setNome("Fake Name");
      inquilino.setApartamento("101");
      inquilino.setBloco("A");
      inquilino.setDocumento(TestRandomValue.generateCpf());
      inquilino.setImagePath("path/to/image");

      return inquilino;
   }

   static User createUser() {
      User user = new User();
      user.id = UUID.randomUUID().toString();
      user.document = TestRandomValue.generateCpf();

      return user;
   }

   static Authorization createAuthorization() {
      Authorization authorization = new Authorization();
      authorization.id = UUID.randomUUID().toString();
      authorization.descPorta = "Fake Description floor gate";
      authorization.tipoPorta = "Gate";

      return authorization;
   }
}
