package dev.alexferreira.sampleapi.usecase;

import dev.alexferreira.sampleapi.domain.user.User;
import dev.alexferreira.sampleapi.domain.user.UserRepository;
import dev.alexferreira.sampleapi.usecase.input.RegisterInquilinoInput;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegisterUser {

      final UserRepository repository;

      public RegisterUser(UserRepository repository) {this.repository = repository;}

      @Transactional
      public void execute(RegisterInquilinoInput input) {
         saveUser(input);
      }

      private void saveUser(RegisterInquilinoInput input) {
            User user = new User();
            user.id = input.inquilinoId.toString();
            user.document = input.document;

            repository.save(user);
      }
}
