package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "requests")
public class AdoptionApplications extends BaseEntity{
	
	@NotEmpty
    @Column(name = "description")
    private String description;

    @ManyToOne
    private Owner owner;

    @ManyToOne
    private Pet pet;

    /**
     * Getter for property description.
     * @return Value of property description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for property description.
     * @param description New value of property description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}