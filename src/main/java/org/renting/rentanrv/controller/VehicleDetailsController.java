package org.renting.rentanrv.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.renting.rentanrv.model.Booking;
import org.renting.rentanrv.model.Vehicle;
import org.renting.rentanrv.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vehicles")
public class VehicleDetailsController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private VehicleService vehicleService;
	
	//localhost/vehicles/:id
	@GetMapping(path = "{id}")
	public String displayVehicle(@ModelAttribute("bookingForm")Booking booking, @PathVariable("id") Long vehicleId, Model model) {
		Vehicle vehicle = vehicleService.getVehicleDetails(vehicleId);
		model.addAttribute("vehicle", vehicle);
		return "vehicle-details";
	}
	
	// handles create and update of vehicle
	@PostMapping
	public String submitVehicleForm(@ModelAttribute("vehicleForm")@Valid Vehicle vehicle, BindingResult result, 
			HttpServletResponse response) {
		
		if (result.hasErrors()) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			result.reject("vehicleForm.error.imcompleteInput");
			logger.warn(vehicle.toString());
			return "vehicle-form";
		} else {
			vehicleService.createNewVehicle(vehicle);
			return "redirect:/vehicles";
		}
		
	}
	@Secured("ROLE_ADMIN")
	@DeleteMapping(path = "{id}")
	public String deleteVehicle(@PathVariable("id") Long vehicleId) {
		vehicleService.deleteVehicleById(vehicleId);
		return "redirect:/vehicles";
	}
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/edit/{id}")
	public String editVehicle(@PathVariable("id") Long vehicleId, Model model) {
		model.addAttribute("vehicleForm", vehicleService.getVehicleById(vehicleId));
		//add user ?
		return "vehicle-form";
		//return vehicles/vehicleForm
	}
}
