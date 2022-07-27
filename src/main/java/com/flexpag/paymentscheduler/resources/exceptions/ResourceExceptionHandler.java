package com.flexpag.paymentscheduler.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.flexpag.paymentscheduler.services.exceptions.ResourceAccessDenied;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceAccessDenied.class)
	public ResponseEntity<StandardError> resourceAccessDenied(ResourceAccessDenied e, HttpServletRequest request){
		String error = "Access Denied";
		HttpStatus status = HttpStatus.FORBIDDEN;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
