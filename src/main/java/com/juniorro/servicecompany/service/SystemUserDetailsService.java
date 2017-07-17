/*package com.juniorro.servicecompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.juniorro.servicecompany.model.SystemUser;

@Service
public class SystemUserDetailsService implements UserDetailsService {
	
	//private static final String ROLE_USER = "ROLE_USER";
	
	@Autowired
	private SystemUserService systemUserService;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final SystemUser systemUser = systemUserService.findByUsername(username);
		if (systemUser == null){
			throw new UsernameNotFoundException ("No user was found with username " + username);
		}
		return new User(systemUser.getUsername(), systemUser.getPassword(), systemUser.isEnabled(), true, true, true, systemUser.getAuthorities());
	}
	

	private Collection<? extends GrantedAuthority> getAuthoriries(String role) {
		return Arrays.asList(new SimpleGrantedAuthority(role));
	}

}
*/