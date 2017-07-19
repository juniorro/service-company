package com.juniorro.servicecompany.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.juniorro.servicecompany.model.Services;

public interface ServicesRepo extends JpaRepository<Services, Long> {


	long count();

}
