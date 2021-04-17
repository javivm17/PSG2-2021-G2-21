package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "causes")
public class Cause {
	
	private String description;
	
	@NotBlank
	private String organization;
	
	@NotBlank
	@Min(value=0)
	private Integer target;

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getorganization() {
		return this.organization;
	}

	public void setorganization(final String organization) {
		this.organization = organization;
	}

	public Integer getTarget() {
		return this.target;
	}

	public void setTarget(final Integer target) {
		this.target = target;
	}

}
