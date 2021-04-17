package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

public class Donation extends NamedEntity{

	//atributos
	@Column(name = "donation_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate date;
	
	@Column(name = "amount")
	@NotBlank
	private Integer amount;
	
	
	//relaciones
	@JoinColumn(name = "cause_id")
	@ManyToOne
	private Cause cause;
	
	@JoinColumn(name = "owner_id")
	@ManyToOne
	private Owner client; //owner o user???

	
	public LocalDate getDate() {
		return this.date;
	}

	
	public void setDate(final LocalDate date) {
		this.date = LocalDate.now();
	}

	
	public Integer getAmount() {
		return this.amount;
	}

	
	public void setAmount(final Integer amount) {
		this.amount = amount;
	}

	
	public Cause getCause() {
		return this.cause;
	}

	
	public void setCause(final Cause cause) {
		this.cause = cause;
	}

	
	public Owner getClient() {
		return this.client;
	}

	
	public void setClient(final Owner client) {
		this.client = client;
	}
	
	
	
}
