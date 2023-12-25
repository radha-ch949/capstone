package com.example.caps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.caps.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
