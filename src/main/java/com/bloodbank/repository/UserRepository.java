package com.bloodbank.repository;

import org.springframework.data.repository.CrudRepository;

import com.bloodbank.model.User;


public interface UserRepository extends CrudRepository<User, Integer>{
	User findByUsername(String username);

}
