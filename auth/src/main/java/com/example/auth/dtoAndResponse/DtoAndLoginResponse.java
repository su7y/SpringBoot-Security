package com.example.auth.dtoAndResponse;

public class DtoAndLoginResponse {
	
	private String loginToken;
	private long loginExpireTime;
	
	
	
	public DtoAndLoginResponse(String loginToken, long loginExpireTime) {
		super();
		this.loginToken = loginToken;
		this.loginExpireTime = loginExpireTime;
	}
	public String getLoginToken() {
		return loginToken;
	}
	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}
	public long getLoginExpireTime() {
		return loginExpireTime;
	}
	public void setLoginExpireTime(long loginExpireTime) {
		this.loginExpireTime = loginExpireTime;
	}
//	@Override
//	public String toString() {
//		return "DtoAndLoginResponse [loginToken=" + loginToken + ", loginExpireTime=" + loginExpireTime + "]";
//	}
	

}
