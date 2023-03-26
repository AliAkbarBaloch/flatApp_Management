package com.aliakbar.fleetappmanagement.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CountryController {
	
	@GetMapping("/countries")
	public String getCountries() {
		return "country";
	}
}
