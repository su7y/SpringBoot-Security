package com.example.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.dtoAndResponse.DtoAndLoginResponse;
import com.example.auth.entity.User;
import com.example.auth.service.UserService;
import com.example.auth.serviceImpl.JwtLoadItems;

@RestController
@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtLoadItems jwtLoadItem; 
	
	@PostMapping("/save")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		
		return new ResponseEntity<User>(userService.saveUser(user),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<DtoAndLoginResponse> loginUser(@RequestBody User user){
		
		DtoAndLoginResponse loginResponse = null;
		User userCh=userService.loginUser(user);
		
		if(userCh != null) {
			String token=jwtLoadItem.generateToken(userCh);
			System.out.println(token);
			loginResponse= new DtoAndLoginResponse(token,jwtLoadItem.getExpirationTime());
			return  ResponseEntity.ok(loginResponse);
		}
		
		return new ResponseEntity<DtoAndLoginResponse>(loginResponse,HttpStatus.BAD_REQUEST);
	}

}
