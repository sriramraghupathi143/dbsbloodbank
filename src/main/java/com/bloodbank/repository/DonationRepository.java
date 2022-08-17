package com.bloodbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloodbank.model.Donation;
import com.bloodbank.model.Donor;

public interface DonationRepository extends JpaRepository<Donation,Long>{
	List<Donation> findByBloodGroup(String bloodGroup);
	List<Donation> findByUsername(String username);

}
