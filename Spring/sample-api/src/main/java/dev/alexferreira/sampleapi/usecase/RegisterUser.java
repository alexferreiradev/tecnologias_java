package dev.alexferreira.sampleapi.usecase;

import dev.alexferreira.sampleapi.domain.user.User;
import dev.alexferreira.sampleapi.domain.user.UserRepository;
import dev.alexferreira.sampleapi.usecase.input.RegisterTenantInput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterUser {

   final UserRepository repository;

   public RegisterUser(UserRepository repository) {
      this.repository = repository;
   }

   @Transactional
   public void execute(RegisterTenantInput input) {
      repository.save(toUser(input));
   }

   private User toUser(RegisterTenantInput input) {
      User user = new User();
      user.document = input.document;

      return user;
   }
}
