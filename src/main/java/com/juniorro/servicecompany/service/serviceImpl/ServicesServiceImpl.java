package com.juniorro.servicecompany.service.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juniorro.servicecompany.model.Services;
import com.juniorro.servicecompany.repo.ServicesRepo;
import com.juniorro.servicecompany.service.ServicesService;

@Service
@Transactional
public class ServicesServiceImpl implements ServicesService {

	@Autowired
	ServicesRepo servicesRepo;
	
	@Override
	public List<Services> allServices() {
		return servicesRepo.findAll();
	}

	@Override
	public Services saveService(Services service) {
		return servicesRepo.save(service);
	}

	@Override
	public void delete(Services service) {
		servicesRepo.delete(service);
	}

	@Override
	public Services getOne(Long id) {
		return servicesRepo.findOne(id);
	}


}
