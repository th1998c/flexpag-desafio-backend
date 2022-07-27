package com.flexpag.paymentscheduler.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.flexpag.paymentscheduler.entities.Payment;
import com.flexpag.paymentscheduler.enums.PaymentStatus;
import com.flexpag.paymentscheduler.services.PaymentService;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {
	
	@Autowired
	private PaymentService paymentService;	
	
	//busca todos os pagamentos
	@GetMapping
	public ResponseEntity<List<Payment>> findAll(){
		List<Payment> payments = paymentService.findAll();
		
		return ResponseEntity.ok().body(payments);
	}
	
	//busca pagamento por id
	@GetMapping(value = "/{id}")
	public ResponseEntity<Payment> findById(@PathVariable Long id){
		Payment payment = paymentService.findById(id);
		return ResponseEntity.ok().body(payment);
		
	}
	
	//busca Status do pagamento com o id
	@GetMapping(value = "/status/{id}")
	public ResponseEntity<PaymentStatus> findStatusById(@PathVariable Long id){
		Payment payment = paymentService.findById(id);
		return ResponseEntity.ok().body(payment.getStatus());
		
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<Long> createPayment(@RequestBody Payment payment){
		payment = paymentService.createPayment(payment);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(payment.getId()).toUri();
		return ResponseEntity.created(uri).body(payment.getId());
		
	}
	
	//muda status de pagamento para PAID através do id
	@PutMapping(value = "/pay/{id}")
	public void makeThePayment(@PathVariable Long id){
		paymentService.makeThePayment(id);
	}
	
	//atualiza data e hora do pagamento caso ainda esteja como PENDING via json.
	//caso contrário será apresentada excessão personalizada
		
	// modelo para atualização no postman (obs: necessário colocar como raw e JSON):
		
	//
	//	{
	//		"date": "2022-03-12 00:00:00"
	//	}
	@PutMapping(value = "/UpdateDateOrHour/{id}")
	public ResponseEntity<Payment> updateDateOrHour(@PathVariable Long id, @RequestBody Payment pay){
		pay = paymentService.updateDateOrHour(id, pay);
		return ResponseEntity.ok().body(pay);
	}
	
	//deleta pagamento caso ainda esteja como PENDING, 
	//caso contrário será apresentada excessão personalizada
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
			paymentService.deletePayment(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
