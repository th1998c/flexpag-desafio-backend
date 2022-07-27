package com.flexpag.paymentscheduler.services.exceptions;

public class ResourceAccessDenied extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceAccessDenied(Object id) {
		super("Access Denied to Delete, payment already made.");
	}
	
}
