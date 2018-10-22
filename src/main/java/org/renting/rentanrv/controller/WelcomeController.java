package org.renting.rentanrv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	@GetMapping(value = {"/", "/rvs"})
	public String welcome() {
		return ("index");
	}
}
