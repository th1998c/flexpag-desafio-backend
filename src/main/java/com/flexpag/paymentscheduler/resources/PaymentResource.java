package com.flexpag.paymentscheduler.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexpag.paymentscheduler.PaymentService;
import com.flexpag.paymentscheduler.entities.Payment;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {
	
	@Autowired
	private PaymentService paymentService;	
	
	@GetMapping
	public ResponseEntity<List<Payment>> findAll(){
		List<Payment> payments = paymentService.findAll();
		
		return ResponseEntity.ok().body(payments);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Payment> findById(@PathVariable Long id){
		Payment payment = paymentService.findById(id);
		return ResponseEntity.ok().body(payment);
		
	}
	
	@PutMapping(value = "/pay/{id}")
	public void makeThePayment(@PathVariable Long id){
		paymentService.makeThePayment(id);
	}
	
	
}
