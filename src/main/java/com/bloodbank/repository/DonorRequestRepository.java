package com.bloodbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bloodbank.model.Donor;
import com.bloodbank.model.DonorRequest;
@Repository
public interface DonorRequestRepository extends JpaRepository<DonorRequest, Long>{
	List<DonorRequest> findByUsername(String username);
	

}
