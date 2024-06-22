package com.example.auth.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth.entity.User;
import com.example.auth.repository.UserRepsoitory;
import com.example.auth.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepsoitory userRepsoitory;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public User saveUser(User user) {
		
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		return userRepsoitory.save(user);
	}
	
	@Override
	public User loginUser(User user) {
		
//		String encodeloginpassword= passwordEncoder.encode(user.getUserPassword());
//		System.out.println(encodeloginpassword);
		
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(),user.getUserPassword()));
		User userCheck2=userRepsoitory.findByUserEmail(user.getUserEmail());

        return userCheck2;
	}

	@Override
	public User getUserByEmailId(String userEmailId) {
		
		return userRepsoitory.findByUserEmail(userEmailId);
	}
	
	

}
