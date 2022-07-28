package com.flexpag.paymentscheduler.services.exceptions;

public class ResourceDataDoesNotExist extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceDataDoesNotExist(Object id) {
		super("Informed ID does not exist.");
	}
	
}
