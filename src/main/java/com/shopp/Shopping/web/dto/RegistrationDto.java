package com.shopp.Shopping.web.dto;

import java.math.BigInteger;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class RegistrationDto {
	private String firstName;
	private String lastName;
	private String email;
	private BigInteger phoneno;
	public RegistrationDto(String firstName, String lastName, String email, BigInteger phoneno, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneno = phoneno;
		this.password = password;
	}
	
	private String password;
	public RegistrationDto(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	public RegistrationDto(String email, String password) {
		super();
		
		
		this.email = email;
		this.password = password;
	}
	public RegistrationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public BigInteger getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(BigInteger phoneno) {
		this.phoneno = phoneno;
	}
	
}
