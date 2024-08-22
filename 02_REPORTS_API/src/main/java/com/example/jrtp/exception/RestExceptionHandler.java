package com.example.jrtp.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	@ExceptionHandler(value = ExcelCustomExceptions.class)
	public ResponseEntity<ApiError> handleExcelException(ExcelCustomExceptions e){
		log.error("ExcelCustomExceptions raised in excelReport controller method {}",e);
		ApiError error = new ApiError(HttpStatus.NO_CONTENT,e.getMessage(),new Date());
		return new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value = PdfCustomExceptions.class)
	public ResponseEntity<ApiError> handlePdfException(PdfCustomExceptions e){
		log.error("PdfCustomExceptions raised in pdfReport controller method {}",e);
		ApiError error = new ApiError(HttpStatus.NO_CONTENT,e.getMessage(),new Date());
		return new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
	}
}
