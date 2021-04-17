package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.service.CauseService;
import org.springframework.samples.petclinic.service.DonationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/causes/{causeId}")
public class DonationController {

	private final DonationService donationService;
	private final CauseService causeService;
	
	@Autowired
	public DonationController(final CauseService causeService,
		final DonationService donationService) {
		this.causeService = causeService;
		this.donationService = donationService;
	}
	//lo tiene que arreglar Gonza en cause primero
	@ModelAttribute("cause")
	public Cause findCause(@PathVariable("causeId") final int causeId) {
		return this.causeService.findCauseById(causeId);
	}
	
	//initbinder???
	
	
	@ModelAttribute("donation")
	public Donation loadCauseWithDonation(@PathVariable("causeId") final int causeId) {
		final Cause cause = this.causeService.findCauseById(causeId);
		final Donation donation = new Donation();
		//Cause.add(donation);
		return donation;
	}
}
