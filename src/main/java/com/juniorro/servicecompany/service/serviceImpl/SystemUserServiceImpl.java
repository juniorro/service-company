package com.juniorro.servicecompany.service.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juniorro.servicecompany.model.SystemUser;
import com.juniorro.servicecompany.repo.SystemUserRepo;
import com.juniorro.servicecompany.service.SystemUserService;

@Service
@Transactional
public class SystemUserServiceImpl implements SystemUserService {

	@Autowired
	SystemUserRepo SystemUserRepo;

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
	public boolean checkUserExist(String username, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkUsernameExist(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkEmailExist(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void saveConfirmSystemUser(SystemUser systemUser) {
		SystemUserRepo.save(systemUser);
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
	

}
