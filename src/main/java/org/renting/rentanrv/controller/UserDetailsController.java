package org.renting.rentanrv.controller;

import java.util.List;

import org.renting.rentanrv.model.Booking;
import org.renting.rentanrv.model.User;
import org.renting.rentanrv.model.Vehicle;
import org.renting.rentanrv.service.BookingService;
import org.renting.rentanrv.service.UserService;
import org.renting.rentanrv.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("users")
public class UserDetailsController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookingService bookingService;
 	
	@Autowired
	private VehicleService vehicleService;
	
	// localhost:80/users/:id
	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public String displayUser(@PathVariable("id") Long userId, Model model) {
		User user = userService.getUserDetails(userId);
		List<Vehicle> userVehicles = vehicleService.getVehiclesByUserId(userId);
		List<Booking> userBookings = bookingService.getBookingsByUserId(userId);		
		
		String pageTitle = String.format("%s %s's profile", user.getFirstName(), user.getLastName());
		model.addAttribute("user", user);
		model.addAttribute("userPageTitle", pageTitle);
		model.addAttribute("userVehicles", userVehicles);
		model.addAttribute("userBookings", userBookings);
		return "user-details";
	}
}
