package com.on14june.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.on14june.entities.Role;
import com.on14june.repository.RoleRepository;
import com.on14june.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository repository;

	@Override
	public Role saveRole(Role role) {
		return repository.save(role);
	}

	@Override
	public Role getRoleByName(String role) {
		return repository.findByName(role);
	}

}
