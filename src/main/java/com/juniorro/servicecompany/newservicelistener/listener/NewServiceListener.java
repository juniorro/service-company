package com.juniorro.servicecompany.newservicelistener.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import com.juniorro.servicecompany.model.SystemUser;
import com.juniorro.servicecompany.newservicelistener.OnNewServiceRequest;

@Component
public class NewServiceListener implements ApplicationListener<OnNewServiceRequest> {


	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Environment env;

	@Override
	public void onApplicationEvent(final OnNewServiceRequest event) {
		this.confirmNewService(event);
	}

	private void confirmNewService(final OnNewServiceRequest event) {
		final SystemUser systemUser = event.getSystemUser();
		final SimpleMailMessage email = constructEmailMessage(event, systemUser);
		mailSender.send(email);
	}

	private SimpleMailMessage constructEmailMessage(final OnNewServiceRequest event, final SystemUser systemUser) {
		final String recipientAddress = systemUser.getEmail();
		final String recipientName = systemUser.getFirstName();
		final String subject = "New Service Request Confirmation";
		final String serviceName = event.getService().getName();
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject(subject);
		email.setText("Hello "+recipientName+", \r\n\n\n" + serviceName);
		email.setFrom(env.getProperty("support.email"));
		return email;
	}

}
