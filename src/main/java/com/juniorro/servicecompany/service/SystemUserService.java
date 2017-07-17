/*package com.juniorro.servicecompany.service;

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

	boolean checkUserExist(final String username, final String email);

	boolean checkUsernameExist(final String username);

	boolean checkEmailExist(final String email);

	SystemUser savesystemUser(final SystemUser systemUser, Set<UserRole> userRoles);

	void saveConfirmSystemUser(SystemUser systemUser);

	SystemUser getOne(Long id);

	void delete(long id);	
	
	long count();

}
*/