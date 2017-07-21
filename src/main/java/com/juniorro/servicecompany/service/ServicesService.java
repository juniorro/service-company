package com.juniorro.servicecompany.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.juniorro.servicecompany.model.Services;

@Service
public interface ServicesService {
	
	List<Services> allServices();
	
	Services saveService(Services service);

	Services getOne(Long id);

	void delete(Services service);
	
	

}
