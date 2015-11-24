package com.hangout.amigos.dto;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author puneetpopli
 *
 */

@Document(collection = "login")
public class Login {

	private int userId;

	private String email;

	private String password;

	private String sessionId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	
	
}
