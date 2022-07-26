package com.flexpag.paymentscheduler;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexpag.paymentscheduler.entities.Payment;
import com.flexpag.paymentscheduler.enums.PaymentStatus;
import com.flexpag.paymentscheduler.repositories.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	public List<Payment> findAll(){
		return paymentRepository.findAll();
	}
	
	public Payment findById(Long id) {
		Optional<Payment> payment = paymentRepository.findById(id);
		return payment.get();
	}
	
	public Payment makeThePayment(Long id) {
		Payment p = paymentRepository.getOne(id);
		updateData(p);
		return paymentRepository.save(p);
	}

	private void updateData(Payment p) {
		p.setStatus(PaymentStatus.PAID);		
	}
	
}
