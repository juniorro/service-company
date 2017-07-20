package com.juniorro.servicecompany.service.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juniorro.servicecompany.model.SystemUser;
import com.juniorro.servicecompany.model.security.UserRole;
import com.juniorro.servicecompany.repo.SystemUserRepo;
import com.juniorro.servicecompany.service.RoleService;
import com.juniorro.servicecompany.service.SystemUserService;

@Service
@Transactional
public class SystemUserServiceImpl implements SystemUserService {

	@Autowired
	SystemUserRepo SystemUserRepo;

	@Autowired
	private RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<SystemUser> allsystemUser() {
		return SystemUserRepo.findAll();
	}

	@Override
	public SystemUser findByUsername(String username) {
		return SystemUserRepo.findByUsername(username);
	}

	@Override
	public SystemUser findByEmail(String email) {
		return SystemUserRepo.findByEmail(email);
	}

	@Override
	public SystemUser findById(Long id) {
		return SystemUserRepo.findOne(id);
	}

	@Override
	public SystemUser getOne(Long id) {
		return SystemUserRepo.findOne(id);
	}

	@Override
	public void delete(long id) {
		SystemUserRepo.delete(id);
	}

	@Override
	public long count() {
		return SystemUserRepo.count();
	}

	@Override
	public SystemUser saveUser(final SystemUser systemUser, Set<UserRole> userRoles) {
		for (UserRole roles : userRoles) {
			roleService.save(roles.getRole());
		}
		String encryptpassword = bCryptPasswordEncoder.encode(systemUser.getPassword());
		systemUser.setPassword(encryptpassword);
		systemUser.getSystemUserRoles().addAll(userRoles);
		SystemUserRepo.save(systemUser);
		return systemUser;
	}

}
