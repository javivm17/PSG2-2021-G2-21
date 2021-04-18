package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.AdoptionApplications;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.AdoptionRequestService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adoption")

public class AdoptionController {
	
	private PetService petService;
	
	private OwnerService ownerService;
	
	private AdoptionRequestService adoptionRequestService;

	
	@Autowired
	public AdoptionController(PetService petService,OwnerService ownerService, AdoptionRequestService adoptionRequestService) {
		this.petService = petService;
		this.ownerService= ownerService;
		this.adoptionRequestService= adoptionRequestService;
	}

	@GetMapping(value = "/list")
    public String showAdoptablePetsList(Map<String, Object> model) {
		model.put("pets", petService.findAdoptablePets());
		model.put("owners", petService.findOwners());
		return "adoption/listAdoptablePets";
	}
	
	@GetMapping(value="/requests")
	public ModelAndView getPendingRequests(Principal principal) {
		Owner owner = ownerService.getOwnerByUserName(principal.getName());
		List<AdoptionApplications>pendingRequets = adoptionRequestService.getRequests(owner); 
		ModelAndView mov = new ModelAndView("adoption/pendingRequests");
		mov.addObject("requests", pendingRequets);
		mov.addObject("numRequests",pendingRequets.size());
		return mov;	
	}
	
	@GetMapping(value="/accept/{reqId}")
	public String acceptRequest(@PathVariable("reqId") Integer reqId, Principal principal) throws DataAccessException, DuplicatedPetNameException {
		Owner ownerLogeado = ownerService.getOwnerByUserName(principal.getName());
		AdoptionApplications request = adoptionRequestService.getRequestById(reqId).orElse(null);
		
		if(request.getPet().getOwner().getId().equals(ownerLogeado.getId()))
			this.adoptionRequestService.acceptRequest(request);
		return "redirect:/adoption/requests";
	}
	
	@GetMapping(value="/deny/{reqId}")
	public String denyString(@PathVariable("reqId") Integer reqId, Principal principal) {
		Owner ownerLogeado = ownerService.getOwnerByUserName(principal.getName());
		AdoptionApplications request = adoptionRequestService.getRequestById(reqId).orElse(null);
		
		if(request.getPet().getOwner().getId().equals(ownerLogeado.getId()))
			adoptionRequestService.removeRequest(request.getId());
			
		return "redirect:/adoption/requests";
	}
	
}
