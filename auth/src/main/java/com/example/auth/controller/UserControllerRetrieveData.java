package com.example.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.entity.User;

@RestController
@RequestMapping("/user")
public class UserControllerRetrieveData {
	
	
	@GetMapping("/get")
	public ResponseEntity<User> getAuthenticateUser(){
		
		Authentication authenticateUser = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println("thuis authentication token"+authenticateUser);
		User getUserinAuthenticate =(User) authenticateUser.getPrincipal();
		
		
		
		return ResponseEntity.ok(getUserinAuthenticate);
	}

}
