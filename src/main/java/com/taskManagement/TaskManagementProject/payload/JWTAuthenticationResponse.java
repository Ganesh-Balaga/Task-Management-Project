package com.taskManagement.TaskManagementProject.payload;

public class JWTAuthenticationResponse {

	private String token;
	private String tokenType = "Bearer";
	
	public JWTAuthenticationResponse(String token) {
		this.token = token;
		
	
	}

	public String getToken() {
		return token;
	}

	public String getTokenType() {
		return tokenType;
	}
}
