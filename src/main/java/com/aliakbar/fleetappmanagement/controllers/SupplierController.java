package com.aliakbar.fleetappmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SupplierController {
	@GetMapping("/suppliers")
	public String getSuppliers() {
		return "supplier";
	}

}
