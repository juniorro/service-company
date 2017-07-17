/*package com.juniorro.servicecompany.registrationlistener.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.juniorro.servicecompany.model.SystemUser;
import com.juniorro.servicecompany.registrationlistener.OnRegistrationCompleteEvent;
import com.juniorro.servicecompany.service.VerificationTokenService;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	@Autowired
	private VerificationTokenService verificationTokenService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Environment env;

	@Override
	public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
	}

	private void confirmRegistration(final OnRegistrationCompleteEvent event) {
		final SystemUser systemUser = event.getSystemUser();
		final String token = UUID.randomUUID().toString();
		verificationTokenService.createVerificationToken(systemUser, token);
		final SimpleMailMessage email = constructEmailMessage(event, systemUser, token);
		mailSender.send(email);
	}

	private SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final SystemUser systemUser,
			final String token) {
		final String recipientAddress = systemUser.getEmail();
		final String subject = "Registration Confirmation - Please Confirm Your Email Address";
		final String confirmationUrl = event.getAppUrl()+"/confirm?token=" + token;
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject(subject);
		email.setText("Please open the following URL to verify your account: \r\n\n\n" + confirmationUrl);
		email.setFrom(env.getProperty("support.email"));
		return email;
	}

}
*/