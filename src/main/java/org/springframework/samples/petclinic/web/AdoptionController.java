package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adoption")

public class AdoptionController {
	
	private PetService petService;
	
	@Autowired
	public AdoptionController(PetService petService) {
		this.petService = petService;
	}

	@GetMapping(value = "/list")
    public String showAdoptablePetsList(Map<String, Object> model) {
		model.put("pets", petService.findAdoptablePets());
		model.put("owners", petService.findOwners());
		return "adoption/listAdoptablePets";
	}
}
