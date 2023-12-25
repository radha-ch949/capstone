package com.example.caps.repository;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.caps.model.Admin;
import com.example.caps.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findUserByEmail(String email);

	static void save(Admin user) {
		// TODO Auto-generated method stub
		
	}


}
