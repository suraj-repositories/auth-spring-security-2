package com.on14june.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.on14june.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String name);
}
