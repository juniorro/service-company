package com.juniorro.servicecompany.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.juniorro.servicecompany.model.Services;

@Service
public interface ServicesService {
	
	List<Services> allServices();
	
	Services saveService(Services service);

	void delete(Long id);

	Services getOne(Long id);
	
	

}
