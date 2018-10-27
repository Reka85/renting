package org.renting.rentanrv.controller;

import org.renting.rentanrv.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VehicleListController {
	@Autowired
	private VehicleService vehicleService;
	
	@GetMapping({"/", "/vehicles"})
	public String displayVehicleList(Model model, Pageable page) {
		if (page.getPageSize() < 1 || page.getPageSize() > 10) {
            page = PageRequest.of(0, 5);
        }
		model.addAttribute("allVehicles", vehicleService.getAllVehicles(page));
		return "vehicle-list";
	}
}
