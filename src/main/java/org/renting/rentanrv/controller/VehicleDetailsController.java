package org.renting.rentanrv.controller;

import org.renting.rentanrv.model.Vehicle;
import org.renting.rentanrv.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("vehicles")
public class VehicleDetailsController {
	@Autowired
	private VehicleService vehicleService;
	
	//localhost:80/vehicles/:id ??? name
	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public String displayVehicle(@PathVariable("id") Long vehicleId, Model model) {
		Vehicle vehicle = vehicleService.getVehicleDetails(vehicleId);
		model.addAttribute("vehicle", vehicle);
		return "vehicle-details";
	}
}
