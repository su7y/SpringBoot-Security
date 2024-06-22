package com.example.auth.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="User_Name")
	private String userName;
	
	@Column(name="User_Password")
	private String userPassword;
	
	@Column(name="User_PhoneNo")
	private String userPhoneNo;
	
	@Column(name="User_Email",unique=true)
	private String userEmail;
	
	public User(long id, String userName, String userPassword, String userPhoneNo, String userEmail) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userPhoneNo = userPhoneNo;
		this.userEmail = userEmail;
	}
	public User() {
		
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return List.of();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhoneNo() {
		return userPhoneNo;
	}

	public void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userPassword;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userEmail;
	}
	

}
