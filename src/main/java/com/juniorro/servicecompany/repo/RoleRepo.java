package com.juniorro.servicecompany.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniorro.servicecompany.model.security.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

	Role findByName(String name);

}
