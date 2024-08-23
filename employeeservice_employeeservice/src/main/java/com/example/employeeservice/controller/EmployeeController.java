package com.example.employeeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EmployeeController{
	@Autowired
	private RestTemplate rt;
	
	@GetMapping("/employee")
	public String getEmployee(){
		
		String address = rt.getForObject("http://localhost:8080/address",String.class);
		return "ramaya" +address;
	}
}