package com.bloodbank.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class DonorUpdate {
	
	private long id;

	private String username;
	
	private String patientName;

	private String patientAge;
	
	private String reason;
	
	private String bloodGroup;
	
	private float units;
	
	private String status;
	

}
