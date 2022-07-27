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
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
@Entity
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double valueOfPayment;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private Instant date;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "GMT")
	private Instant hourOfPayment;
	@NonNull
	private PaymentStatus status;

	public Payment() {

	}

	public Payment(Long id, Double valueOfPayment, Instant date) {
		this.id = id;
		this.valueOfPayment = valueOfPayment;
		this.date = date;
		this.hourOfPayment = this.date;
		this.status = PaymentStatus.PENDING;
	}

}
