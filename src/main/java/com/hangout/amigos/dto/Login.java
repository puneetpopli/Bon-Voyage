package com.hangout.amigos.dto;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author puneetpopli
 *
 */

@Document(collection = "login")
public class Login {

	private Integer userId;

	private String email;

	private String password;

	private Integer sessionId;

	


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
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

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}


	
	
}
