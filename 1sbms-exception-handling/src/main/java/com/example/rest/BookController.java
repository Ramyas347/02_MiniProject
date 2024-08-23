package com.example.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.NoDataFoundException;

@RestController
public class BookController {

	@GetMapping("/book/{isbn}")
	public String getPrice(@PathVariable String isbn) {
		
		String msg = "";
		if(isbn.equals("ISBN001")) {
			msg = "ValidSSN";
		}else {
			throw new NoDataFoundException("InvalidSSN");
		}
		return msg;
		
	}
}
