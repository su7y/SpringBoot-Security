package com.example.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.auth.repository.UserRepsoitory;
import com.example.auth.service.UserService;

@Configuration
public class ApplicationConfiguration {
	
	@Autowired
	private UserRepsoitory userRepsoitory;
	
	@Bean
	BCryptPasswordEncoder password() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		
		return config.getAuthenticationManager();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		
		return userEmail -> userRepsoitory.findByUserEmail(userEmail);
				//.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		
	}
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(password());
		return provider;
	}

}
