package org.renting.rentanrv.controller;

import org.renting.rentanrv.service.UserService;
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
	
	// localhost:80/users/:id
	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public String displayUser(@PathVariable("id") Long userId, Model model) {
		model.addAttribute("user", userService.getUserDetails(userId));
		return "user-details";
	}
	
	
}
