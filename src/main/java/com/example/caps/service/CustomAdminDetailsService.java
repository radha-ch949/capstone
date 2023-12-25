package com.example.caps.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.caps.model.Admin;
import com.example.caps.model.CustomAdminDetails;
import com.example.caps.repository.AdminRepository;

@Service

public class CustomAdminDetailsService implements UserDetailsService{
	@Autowired
	AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Admin> user = adminRepository.findUserByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException("User to not know"));
		return user.map(CustomAdminDetails::new).get();
	}

}