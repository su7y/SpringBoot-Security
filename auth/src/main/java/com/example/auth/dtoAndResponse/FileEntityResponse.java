package com.example.auth.dtoAndResponse;

public class FileEntityResponse {
	
	private String fileName;
	private String fileContentType;
	private String downloadUri;
	private long fileSize;
	
	public FileEntityResponse(String fileName, String fileContentType, String downloadUri, long fileSize) {
		super();
		this.fileName = fileName;
		this.fileContentType = fileContentType;
		this.downloadUri = downloadUri;
		this.fileSize = fileSize;
	}
	
	public FileEntityResponse() {
		
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getDownloadUri() {
		return downloadUri;
	}

	public void setDownloadUri(String downloadUri) {
		this.downloadUri = downloadUri;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	

}
