package com.bloodbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bloodbank.model.Acceptor;


@Repository
public interface AcceptorRepository extends JpaRepository<Acceptor, Long> {
	List<Acceptor> findByBloodGroup(String bloodGroup);

}
