package com.bloodbank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mail {
	
	private String username;
	private String email;
	private String message;
	private String phone;
	private String reciverEmail;



}
