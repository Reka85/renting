package org.renting.rentanrv.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.renting.rentanrv.model.Vehicle;
import org.renting.rentanrv.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	
	//localhost:80/vehicles/:id
	@GetMapping(path = "{id}")
	public String displayVehicle(@PathVariable("id") Long vehicleId, Model model) {
		Vehicle vehicle = vehicleService.getVehicleDetails(vehicleId);
		model.addAttribute("vehicle", vehicle);
		return "vehicle-details";
	}
	
	@PostMapping
	public String submitVehicleForm(@ModelAttribute("vehicleForm")@Valid Vehicle vehicle, BindingResult result, 
			HttpServletResponse response) {
		
		String view = "vehicle-form";
		
		if (result.hasErrors()) {
			logger.warn(result.toString());
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			result.reject("vehicleForm.error.imcompleteInput");
		} else {
			vehicleService.createNewVehicle(vehicle);
			view = "redirect:/vehicles"; //rather to users own page
		}
		return view;
	}
}
