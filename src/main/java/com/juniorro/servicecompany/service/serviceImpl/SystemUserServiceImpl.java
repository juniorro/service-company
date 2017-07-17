package com.juniorro.servicecompany.service.serviceImpl;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.juniorro.patientappointmentsystem.Service.CustomerService;
import com.juniorro.patientappointmentsystem.Service.RoleService;
import com.juniorro.patientappointmentsystem.model.Customer;
import com.juniorro.patientappointmentsystem.model.security.UserRole;
import com.juniorro.patientappointmentsystem.repo.CustomerRepo;

@Service
@Transactional
public class SystemUserServiceImpl implements CustomerService {

	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Customer findByUsername(String username) {
		return customerRepo.findByUsername(username);
	}

	@Override
	public Customer findByEmail(String email) {
		return customerRepo.findByEmail(email);
	}

	@Override
	public Customer findById(Long id) {
		return customerRepo.findOne(id);
	}

	@Override
	public Customer saveCustomer(final Customer customer, Set<UserRole> userRoles) {
		 for (UserRole roles : userRoles) {
             roleService.save(roles.getRole());
         }
		 String encryptpassword = bCryptPasswordEncoder.encode(customer.getPassword());
		 customer.setPassword(encryptpassword);
		 customer.getCustomerRoles().addAll(userRoles);
		 customerRepo.save(customer);
		return customer;
	}

	public boolean checkUsernameExist(String username) {
		if (null != findByUsername(username)) {
			return true;
		}

		return false;
	}

	public boolean checkEmailExist(String email) {
		if (null != findByEmail(email)) {
			return true;
		}

		return false;
	}

	public boolean checkUserExist(String username, String email) {
		if (checkUsernameExist(username) || checkEmailExist(username)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void saveConfirmCustomer(Customer customer) {
		customerRepo.save(customer);
	}

	@Override
	public List<Customer> allCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public Customer getOne(Long id) {
		return customerRepo.findOne(id);
	}

	@Override
	public void delete(long id) {
		customerRepo.delete(id);
	}

	@Override
	public long count() {
		return customerRepo.count();
	}



}
