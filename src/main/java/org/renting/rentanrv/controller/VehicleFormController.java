package org.renting.rentanrv.controller;

import org.renting.rentanrv.model.Vehicle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class VehicleFormController {
	@GetMapping("/vehicles/new")
	public String displayForm(@ModelAttribute("vehicleForm")Vehicle v) {
		return "vehicle-form";
	}
}
