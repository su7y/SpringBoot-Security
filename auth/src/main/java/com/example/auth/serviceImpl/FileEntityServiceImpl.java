package com.example.auth.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.example.auth.entity.FileEntity;
import com.example.auth.repository.FileEntityRepository;
import com.example.auth.service.FileEntityService;
@Service
public class FileEntityServiceImpl implements FileEntityService{
	
	@Autowired
	private FileEntityRepository fileEntityRepository;

	@Override
	public FileEntity savefile(MultipartFile file) throws Exception {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			
			if(fileName.contains("..")) {
				throw new Exception("This File is Invalid"+fileName);
			}
			
			if(file.getBytes().length > 1024*1024) {
				throw new Exception("this file was exceed the size"+file.getBytes().length);
			}
			
			FileEntity toSetValue = new FileEntity(fileName,file.getContentType(),file.getBytes());
			
			return fileEntityRepository.save(toSetValue);
		
		}catch(MaxUploadSizeExceededException e) {
			
			throw new MaxUploadSizeExceededException(file.getSize());
			
		}catch(Exception e) {
			
			throw new Exception("this file is not save "+ e);
		}
		
		
	}
	
	

}
