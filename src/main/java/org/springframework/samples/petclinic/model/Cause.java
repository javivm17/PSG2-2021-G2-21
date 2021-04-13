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
	private String organitation;
	
	@NotBlank
	@Min(value=0)
	private Integer target;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrganitation() {
		return organitation;
	}

	public void setOrganitation(String organitation) {
		this.organitation = organitation;
	}

	public Integer getTarget() {
		return target;
	}

	public void setTarget(Integer target) {
		this.target = target;
	}

}
