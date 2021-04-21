package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.AdoptionApplications;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class AdoptionValidator implements Validator {

	@Override
	public void validate(Object obj, Errors errors) {
		AdoptionApplications adoption = (AdoptionApplications) obj;
		String description = adoption.getDescription();
		if (description.isEmpty()||description.isBlank()||description==null) {
			errors.rejectValue("description", "Debe rellenar este campo");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return AdoptionApplications.class.isAssignableFrom(clazz);
	}

}

