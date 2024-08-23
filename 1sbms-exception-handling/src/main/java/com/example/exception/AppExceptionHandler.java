package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.binding.ExceptionInfo;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = ArithmeticException.class)
	public ResponseEntity<ExceptionInfo> handleAE(ArithmeticException ae){
		ExceptionInfo exception = new ExceptionInfo();
		exception.setErrMsg(ae.getMessage());
		exception.setErrCode("AIT0004");
		
		return new ResponseEntity<>(exception,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(value = NoDataFoundException.class)
	public ResponseEntity<ExceptionInfo> handleNDF(NoDataFoundException ndf){
		ExceptionInfo exception = new ExceptionInfo();
		exception.setErrMsg(ndf.getMessage());
		exception.setErrCode("AIT0005");
		
		return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
	}
}
