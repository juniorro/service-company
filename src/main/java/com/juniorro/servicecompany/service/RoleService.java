package com.juniorro.servicecompany.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.juniorro.servicecompany.model.Services;
import com.juniorro.servicecompany.model.security.Role;

@Service
public interface RoleService {

	Role findByName(String string);

	Role save(Role role);
	
	
	
	

}
