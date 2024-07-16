package com.example.auth.controller;


import java.io.File;
import java.util.HashSet;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.auth.dtoAndResponse.FileEntityResponse;
import com.example.auth.entity.FileEntity;
import com.example.auth.entity.User;
import com.example.auth.service.FileEntityService;
import com.example.auth.serviceImpl.JwtLoadItems;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserControllerRetrieveData {
	
	@Autowired
	private JwtLoadItems jwtLoadItems;
	
	@Autowired
	private FileEntityService fileEntityService;
	
	@GetMapping("/get")
	public ResponseEntity<User> getAuthenticateUser(){
		
		Authentication authenticateUser = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println("thuis authentication token"+authenticateUser);
		User getUserinAuthenticate =(User) authenticateUser.getPrincipal();
		
		
		
		return ResponseEntity.ok(getUserinAuthenticate);
	}
	
//	@DeleteMapping("/logout")
//	public ResponseEntity<String> logoutUser(){                  (This method only authentication can make false not a token )
//		
//		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
//		if(authentication.isAuthenticated()) {
//		authentication.setAuthenticated(false);
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		SecurityContextHolder.clearContext();
//		System.out.println("Successfully logout...!");
//		}else {
//			return new ResponseEntity<String>("Something is wrong in Authentication",HttpStatus.BAD_REQUEST);
//		}
//		
//		return new ResponseEntity<String>("Successfully logout",HttpStatus.OK);
//	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logoutUser(HttpServletRequest request){
		
		final String getAuthHeader = request.getHeader("Authorization");
		String token = getAuthHeader.substring(7);
		System.out.println("this logout user token"+token);
		jwtLoadItems.tokenToLogout(token);
		return new ResponseEntity<String>("Successfully logout..!",HttpStatus.OK);
		
		
	}
	
	@PostMapping("/savefile")
	public ResponseEntity<FileEntityResponse> saveFile(@RequestParam("file") MultipartFile file) throws Exception {
		
		String filenameInSystem = file.getOriginalFilename();
		try {
			
			file.transferTo(new File("C:\\Users\\WebTeam\\Documents\\"+filenameInSystem));
			System.out.println("File is Successfully Upload");
			
		}catch(Exception e) {
			throw new Exception("this file is not upload in file system"+ e);
		}
		
		
		FileEntity getSaveFile = fileEntityService.savefile(file);
		
		String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download")
				.path(getSaveFile.getFileName()).toUriString();
		
		FileEntityResponse fileResponse = new FileEntityResponse(getSaveFile.getFileName(),getSaveFile.getFileType(),downloadUrl,file.getSize());
		return new ResponseEntity<FileEntityResponse>(fileResponse,HttpStatus.CREATED);
	}

}
