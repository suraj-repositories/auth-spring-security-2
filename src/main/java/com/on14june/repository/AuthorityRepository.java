package com.on14june.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.on14june.entities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{

	Authority findByName(String name);
}
