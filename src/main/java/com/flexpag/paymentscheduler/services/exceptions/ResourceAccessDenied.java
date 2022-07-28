package com.flexpag.paymentscheduler.services.exceptions;

public class ResourceAccessDenied extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceAccessDenied(Object id) {
		super("Access denied to delete or change, payment already made.");
	}
	
}
