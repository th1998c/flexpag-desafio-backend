package com.flexpag.paymentscheduler.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.paymentscheduler.enums.PaymentStatus;

import lombok.Data;

@Data
@Entity
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant date;
	private PaymentStatus status;

	public Payment() {

	}

	public Payment(Long id, Instant date) {
		this.id = id;
		this.date = date;
		this.status = PaymentStatus.PENDING;
	}

}
