/*package com.juniorro.servicecompany.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import com.juniorro.servicecompany.model.SystemUser;
import com.juniorro.servicecompany.model.security.PasswordResetToken;

@Service
public interface PasswordResetTokenService {

	PasswordResetToken findByToken(final String token);
	
	void createPasswordResetToken(SystemUser systemUser, String token);
	
	SimpleMailMessage constructResetTokenEmail(final String contextPath, final String token, final SystemUser systemUser);

	void changePassword(SystemUser systemUser, String password);

	void delete(long id);
}
*/