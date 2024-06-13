package com.example.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth.entity.User;

public interface UserRepsoitory extends JpaRepository<User,Long> {

	User findByUserEmailAndUserPassword(String userEmail, String encodeloginpassword);

	User findByUserEmail(String userEmail);

}
