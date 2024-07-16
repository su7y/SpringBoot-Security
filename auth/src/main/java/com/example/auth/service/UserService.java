package com.example.auth.service;

import com.example.auth.entity.User;

public interface UserService {

	User saveUser(User user);

	User loginUser(User user);
	
	User getUserByEmailId(String userEmailId);

}
