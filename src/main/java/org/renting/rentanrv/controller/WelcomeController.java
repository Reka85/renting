package org.renting.rentanrv.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class WelcomeController {
	@PostConstruct
	public void welcome() {
		System.out.println ("welcome");
	}
}
