package org.springframework.samples.petclinic.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Booking;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.samples.petclinic.service.BookingService;

public class BookingValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Booking.class.isAssignableFrom(clazz);
	}

	@Autowired
	private BookingService bookingService;
	
	@Override
	public void validate(Object target, Errors errors) {
		Booking booking = (Booking) target;
		   		
        if (booking.getInitialDate()==null) {
        	errors.rejectValue("initialDate", "Requerido", "Requerido");	
        }
        			
        if (booking.getEndDate()==null) {
        	errors.rejectValue("endDate", "Requerido", "Requerido");	
        }
        			
        if (booking.getEndDate()!=null && booking.getInitialDate()!=null && booking.getInitialDate().isAfter(booking.getEndDate())) {
        	errors.rejectValue("endDate", "La fecha de fin debe ser posterior a la fecha de fecha de inicio", "La fecha de fin debe ser posterior a la fecha de fecha de inicio");
        }
        
		List<Booking> hotels = this.bookingService.listBookingsByPetId(booking.getPet().getId());
    	if (!(booking.getInitialDate() == null || booking.getEndDate() == null)) {
    		for(int i=0;i<hotels.size();i++) {
    			// A corresponde a la fecha de inicio de la reserva realizada previamente, B corresponde a la fecha de fin de la reserva realizada previamente
    			// C corresponde a la fecha de inicio de la nueva reserva, D corresponde a la fecha de fin de la nueva reserva
        		boolean AantesdeC = booking.getPet().getBookings().get(i).getInitialDate().isBefore(booking.getInitialDate());
        		boolean BdespuesdeC = booking.getPet().getBookings().get(i).getEndDate().isAfter(booking.getInitialDate());
        		boolean AantesdeD = booking.getPet().getBookings().get(i).getInitialDate().isBefore(booking.getEndDate());
        		boolean BdespuesdeD = booking.getPet().getBookings().get(i).getEndDate().isAfter(booking.getEndDate());
        		boolean AigualC = booking.getPet().getBookings().get(i).getInitialDate().equals(booking.getInitialDate());
        		boolean BigualD = booking.getPet().getBookings().get(i).getEndDate().equals(booking.getEndDate());
        		boolean AdespuesdeC = booking.getPet().getBookings().get(i).getInitialDate().isAfter(booking.getInitialDate());
        		boolean BantesdeD = booking.getPet().getBookings().get(i).getEndDate().isBefore(booking.getEndDate());
        		
        		if((AantesdeC || AigualC) &&	BdespuesdeC || AantesdeD && (BdespuesdeD || BigualD) || (AigualC && BigualD) || (AdespuesdeC && BantesdeD)) {
        			errors.rejectValue("endDate", "Existe una reserva para esta mascota en estas mismas fechas", "Existe una reserva para esta mascota en estas mismas fechas");
        		}
    		}
    	}
	}
}
