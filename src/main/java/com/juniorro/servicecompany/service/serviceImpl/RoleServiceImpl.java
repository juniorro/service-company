package com.juniorro.servicecompany.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juniorro.servicecompany.model.security.Role;
import com.juniorro.servicecompany.repo.RoleRepo;
import com.juniorro.servicecompany.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepo roleRepo; 

	@Override
	public Role findByName(String name) {
		return roleRepo.findByName(name);
	}

	@Override
	public Role save(Role role) {
		return roleRepo.save(role);
	}
}
