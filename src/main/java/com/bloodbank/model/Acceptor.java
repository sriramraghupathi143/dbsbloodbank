package com.bloodbank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Acceptor_mstr")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Acceptor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String username;
	@Column
	private String bloodGroup;
	@Column
	private String email;
	@Column
	private String phone;
	@Column
	private String address;
	
	

}
