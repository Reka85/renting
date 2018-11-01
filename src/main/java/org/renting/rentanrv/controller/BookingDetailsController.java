package org.renting.rentanrv.controller;

import org.renting.rentanrv.model.Booking;
import org.renting.rentanrv.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookingDetailsController {
	
	@Autowired
	private BookingService bookingService;
	
	//localhost/bookings/:id
	@GetMapping(path = "bookings/{id}")
	public String displayVehicle(@PathVariable("id") Long bookingId, Model model) {
		Booking booking = bookingService.getBookingDetails(bookingId);
		model.addAttribute("booking", booking);
		return "booking-details";
	}
	
	@DeleteMapping(path = "bookings/{id}")
	public String deleteBooking(@PathVariable("id") Long bookingId) {
		Booking booking = bookingService.getBookingDetails(bookingId);
		bookingService.deleteBookingById(bookingId);
		return "redirect:/";
	}
}
