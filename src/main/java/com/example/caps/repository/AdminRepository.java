package com.example.caps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.caps.model.Admin;
import com.example.caps.model.User;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	Optional<Admin> findUserByEmail(String email);

	static void save(User user) {
		// TODO Auto-generated method stub
		
	}
}
