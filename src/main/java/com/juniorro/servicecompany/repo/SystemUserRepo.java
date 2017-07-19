package com.juniorro.servicecompany.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.juniorro.servicecompany.model.SystemUser;

public interface SystemUserRepo extends JpaRepository<SystemUser, Long> {


	long count();

	SystemUser findByUsername(String username);

	SystemUser findByEmail(String email);

}
