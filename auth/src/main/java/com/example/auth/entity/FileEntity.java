package com.example.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="files")
public class FileEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="file_Name")
	private String fileName;
	
	@Column(name="file_Type")
	private String fileType;
	
	@Column(name="file_Data")
	@Lob
	private byte[] filedata;

	public FileEntity( String fileName, String fileType, byte[] filedata) {
		super();
		
		this.fileName = fileName;
		this.fileType = fileType;
		this.filedata = filedata;
	}
	
	public FileEntity() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getFiledata() {
		return filedata;
	}

	public void setFiledata(byte[] filedata) {
		this.filedata = filedata;
	}
	
	

}
