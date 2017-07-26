package com.juniorro.servicecompany.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.juniorro.servicecompany.model.SystemUser;
import com.juniorro.servicecompany.model.security.UserRole;

@Service
public interface SystemUserService {

	List<SystemUser> allsystemUser();

	SystemUser findByUsername(final String username);

	SystemUser findByEmail(final String email);

	SystemUser findById(Long id);

	SystemUser getOne(Long id);

	void delete(long id);	
	
	long count();

	SystemUser saveUser(SystemUser customer, Set<UserRole> userRoles);

	void saveUpdate(SystemUser systemUser);

	boolean checkUsernameExist(String username);

}
