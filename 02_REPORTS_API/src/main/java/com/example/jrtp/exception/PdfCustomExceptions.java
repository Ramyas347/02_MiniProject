package com.example.jrtp.exception;

public class PdfCustomExceptions extends Exception{

	public PdfCustomExceptions(String message) {
		super(message);
	}
	
	public PdfCustomExceptions(String message, Throwable cause) {
		super(message,cause);
	}
}
