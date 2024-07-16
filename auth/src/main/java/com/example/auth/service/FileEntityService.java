package com.example.auth.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.auth.entity.FileEntity;

public interface FileEntityService {

	FileEntity savefile(MultipartFile file) throws Exception;
	

}
