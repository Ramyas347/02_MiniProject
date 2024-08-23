package com.example.jrtp.exception;

public class ExcelCustomExceptions extends Exception{

	public ExcelCustomExceptions(String message) {
		super(message);
	}
	
	public ExcelCustomExceptions(String message, Throwable cause) {
		super(message,cause);
	}
}
