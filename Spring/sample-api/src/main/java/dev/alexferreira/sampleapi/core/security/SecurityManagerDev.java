package dev.alexferreira.sampleapi.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Profile("dev2")
public class SecurityManagerDev {

	@Bean
	public UserDetailsService getUserDetailService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("admin").password("{noop}pass").roles("USER").build());

		return manager;
	}
}
