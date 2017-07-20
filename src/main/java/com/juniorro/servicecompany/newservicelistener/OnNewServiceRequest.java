package com.juniorro.servicecompany.newservicelistener;

import org.springframework.context.ApplicationEvent;

import com.juniorro.servicecompany.model.Services;
import com.juniorro.servicecompany.model.SystemUser;

public class OnNewServiceRequest extends ApplicationEvent {

	private final Services service;
	private final SystemUser systemUser;

	public OnNewServiceRequest(final SystemUser systemUser, final Services service) {
		super(systemUser);
		this.systemUser = systemUser;
		this.service = service;
	}

	public Services getService() {
		return service;
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}

}
