package com.flexpag.paymentscheduler.services;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.flexpag.paymentscheduler.entities.Payment;
import com.flexpag.paymentscheduler.enums.PaymentStatus;
import com.flexpag.paymentscheduler.repositories.PaymentRepository;
import com.flexpag.paymentscheduler.services.exceptions.ResourceAccessDenied;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	//busca todos os pagamentos
	public List<Payment> findAll() {
		return paymentRepository.findAll();
	}
	//busca pagamento por id
	public Payment findById(Long id) {
		Optional<Payment> payment = paymentRepository.findById(id);
		return payment.get();
	}
	
	//busca Status do pagamento com o id
	public PaymentStatus findStatusById(Long id) {
		Optional<Payment> payment = paymentRepository.findById(id);
		return payment.get().getStatus();
	}
	
	public Payment createPayment(Payment payment) {
		payment.setStatus(PaymentStatus.PENDING);
		payment.setHourOfPayment(payment.getDate());
		return paymentRepository.save(payment);
	}
	
	
	//muda status de pagamento para PAID através do id
	public Payment makeThePayment(Long id) {
		Payment p = paymentRepository.getOne(id);
		updateStatus(p);
		return paymentRepository.save(p);
	}
	
	//método auxiliar para mudar status
	private void updateStatus(Payment p) {
		p.setStatus(PaymentStatus.PAID);
	}
	
	//deleta pagamento caso ainda esteja como PENDING, 
	//caso contrário será apresentada excessão personalizada
	public void deletePayment(Long id) {
		Payment p = paymentRepository.getOne(id);		
		if(p.getStatus().toString() == "PENDING") {
		paymentRepository.deleteById(p.getId());
		}
		else {
			p.setId(0L);
				try{
					paymentRepository.deleteById(p.getId());
				}catch(EmptyResultDataAccessException e) {
					throw new ResourceAccessDenied(id);
					
				}
			}
		}
	
	//atualiza data e hora do pagamento caso ainda esteja como PENDING via json.
	//caso contrário será apresentada excessão personalizada
	
	// modelo para atualização no postman (obs: necessário colocar como raw e JSON):
	
	//
	//	{
    //		"date": "2022-03-12 00:00:00"
	//	}

	public Payment updateDateOrHour(long id, Payment payment) {
		Payment pay = paymentRepository.getOne(id);
		if(pay.getStatus().toString() == "PENDING") {
		updateDate(pay, payment);
		}
		else {
			pay.setId(0L);
				try{
					paymentRepository.deleteById(pay.getId());
				}catch(EmptyResultDataAccessException e) {
					throw new ResourceAccessDenied(id);
					
				}
			}		
		return paymentRepository.save(pay);
	}
	// método auxiliar para mudar data e hora
	private void updateDate(Payment pay, Payment payment) {
		 pay.setDate(payment.getDate());
		 pay.setHourOfPayment(payment.getDate());
	}
		
}
