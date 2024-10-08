package com.example.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	private Logger logger = LoggerFactory.getLogger(DemoRestController.class);
	@GetMapping("/welcome")
	public String welcome() {
		
		String msg = "Welcome";
		try {
			int i = 10/0;
			}catch(Exception e) {
				logger.error("Exception Occured:: "+ e.getMessage());
				throw new ArithmeticException(e.getMessage());
			}
		return msg;
		
	}

}
