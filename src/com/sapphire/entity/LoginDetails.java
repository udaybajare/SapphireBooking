package com.sapphire.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LoginDetails {
	@Id
	private String userName;

	private String password;
	
	private String role;
	
	

	public LoginDetails(String userName, String password, String role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public LoginDetails() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
