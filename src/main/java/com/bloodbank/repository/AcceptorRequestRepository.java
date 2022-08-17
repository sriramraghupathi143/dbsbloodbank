package com.bloodbank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bloodbank.model.AcceptorRequest;
import com.bloodbank.model.DonorRequest;

@Repository
public interface AcceptorRequestRepository extends JpaRepository<AcceptorRequest, Long>{
	List<AcceptorRequest> findByUsername(String username);
	 

}
