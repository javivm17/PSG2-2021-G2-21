package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Booking;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.BookingService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookingController {

	private BookingService bookingService;
	
	private  PetService petService;


	@Autowired
	public BookingController(BookingService bookingService, PetService petService) {
		this.bookingService = bookingService;
		this.petService = petService;
	}
	
	@InitBinder("booking")
	public void initBookingBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new BookingValidator());
	}
	
	@ModelAttribute("booking")
	public Booking loadPetWithVisit(@PathVariable("petId") int petId) {
		Booking booking = new Booking();
		Pet pet = petService.findPetById(petId);		
		booking.setPet(pet);
		return booking;
	}
	
	@GetMapping(value = "/owners/*/pets/{petId}/booking/new")
	public String showForm(@PathVariable("petId") int petId, Map<String, Object> model) {
		return "pets/createBookingForm"; 
	}
	
	@PostMapping(value = "/owners/{ownerId}/pets/{petId}/booking/new")
	public String processNewVisitForm(@Valid Booking booking, BindingResult result) {
		if (result.hasErrors()) {
			return "pets/createBookingForm";
		}
		else {
			this.bookingService.saveBooking(booking);
			return "redirect:/owners/{ownerId}"; 
		}
	}
	
	@GetMapping("/owners/{ownerId}/pets/{petId}/booking/delete/{bookingId}")
	public String deleteBooking(@PathVariable("bookingId") int bookingId,@PathVariable("ownerId") int ownerId) {
		bookingService.deleteBooking(bookingId);
		return "redirect:/owners/{ownerId}";
	}
	//Esta función que lista los bookings de una pet según el id es dónde creo que está el problema porque realmente no manda la info a ningún sitio ¿?	
	@GetMapping(value = "/owners/*/pets/{petId}/bookings") //La "dirección" esta no creo que esté bien porque el /bookings del final ni existe pero he probado metiendo otras y el error se mantiene. Además no sé cuál sería la correcta
	public String showBookings(@PathVariable int petId, Map<String, Object> model) {
		model.put("bookings", this.petService.findPetById(petId).getBookings());
		return "bookingList";
	}
}
