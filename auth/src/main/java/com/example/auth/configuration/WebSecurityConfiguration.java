package com.example.auth.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class WebSecurityConfiguration {
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private AuthenticationFilterConfig authenticationFilterConfig;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeHttpRequests().requestMatchers("/api/auth/**").permitAll()//
		    .anyRequest().authenticated().and()
		    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		    .authenticationProvider(authenticationProvider)
		    .addFilterBefore(authenticationFilterConfig, UsernamePasswordAuthenticationFilter.class);
		//Starting from Spring Security 4.x, the CSRF protection is enabled by default
		return http.build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:8080"));
		configuration.setAllowedMethods(List.of("GET","POST"));
		configuration.setAllowedHeaders(List.of("Authorizaton","Content-Type"));
		
		UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();
		
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}

}
