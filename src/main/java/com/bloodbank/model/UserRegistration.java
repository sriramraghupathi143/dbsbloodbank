package com.bloodbank.model;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRegistration {

	private String username;
	
	private String password;
	private String bloodGroup;
	private String userType;
	
	private String email;
	private String phone;
	
	private String city;
	
}
