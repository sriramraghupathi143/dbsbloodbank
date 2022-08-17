package com.bloodbank.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DonorRequestModel {
	private String username;
	
	private String patientName;
	
	private String patientAge;
	
	private String reason;
	
	private String bloodGroup;
	
	private float units;

}
