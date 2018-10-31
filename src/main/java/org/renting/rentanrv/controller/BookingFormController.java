package org.renting.rentanrv.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.renting.rentanrv.model.Booking;
import org.renting.rentanrv.model.Vehicle;
import org.renting.rentanrv.service.BookingService;
import org.renting.rentanrv.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookingFormController {
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private BookingService bookingService;

	
//	@GetMapping("/vehicles/{id}/reservation")
//	public String displayReservationForm(@ModelAttribute("bookingForm")Booking booking, Model model, 
//			@PathVariable("id") Long vehicleId) {
//		Vehicle vehicle = vehicleService.getVehicleDetails(vehicleId);
//		model.addAttribute("vehicle", vehicle);
//		return "booking-form";
//	}
	
	@PostMapping("/bookings")
	public String submitBookingForm(@ModelAttribute("bookingForm")@Valid Booking booking, BindingResult result, 
			HttpServletResponse response, @RequestParam Long vehicle, Model model) {

		if (result.hasErrors()) {
			
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			result.reject("bookingForm.error.imcompleteInput");
			Vehicle bookingVehicle = vehicleService.getVehicleDetails(vehicle);
			model.addAttribute("vehicle", bookingVehicle);
			return "vehicle-details";
		} else {
			bookingService.createNewBooking(booking);
			return "redirect:/vehicles";
		}
		
	}
}
