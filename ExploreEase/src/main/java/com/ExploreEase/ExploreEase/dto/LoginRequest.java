package com.ExploreEase.ExploreEase.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @Email
    private String email;

    @NotBlank
    private String password;

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

	public LoginRequest(@Email String email, @NotBlank String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public LoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

    // Getters & setters
    
}