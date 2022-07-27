package com.flexpag.paymentscheduler.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.flexpag.paymentscheduler.entities.Payment;
import com.flexpag.paymentscheduler.repositories.PaymentRepository;

@Configuration
public class Config implements CommandLineRunner {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public void run(String... args) throws Exception {
		Payment payment1 = new Payment(null,50.00, Instant.parse("2022-07-21T19:53:07Z"));
		Payment payment2 = new Payment(null,50.00, Instant.parse("2021-03-11T15:25:09Z"));
		Payment payment3 = new Payment(null,100.00, Instant.parse("2022-01-15T02:40:13Z"));

		paymentRepository.saveAll(Arrays.asList(payment1, payment2, payment3));
	}

}
