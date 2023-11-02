package dev.alexferreira.sampleapi.usecase;

import dev.alexferreira.sampleapi.domain.user.UserRepository;
import dev.alexferreira.sampleapi.usecase.input.RegisterTenantInput;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegisterUser {

	final UserRepository repository;

	public RegisterUser(UserRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public void execute(RegisterTenantInput input) {
	}
}
