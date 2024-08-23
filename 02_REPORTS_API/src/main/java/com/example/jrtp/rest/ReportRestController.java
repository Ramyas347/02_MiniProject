package com.example.jrtp.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jrtp.exception.ExcelCustomExceptions;
import com.example.jrtp.request.SearchRequest;
import com.example.jrtp.response.SearchResponse;
import com.example.jrtp.service.EligibilityService;

@RestController
public class ReportRestController {
	
	private static final Logger log = LoggerFactory.getLogger(ReportRestController.class);

	private final EligibilityService service;
	
	@Autowired
	public ReportRestController(EligibilityService service) {
		this.service = service;
		log.info("ReportRestController initialized with the EligibilityService {}",service.getClass().getSimpleName());
	}
	
	@GetMapping("/plans")
	public ResponseEntity<List<String>> getPlanNames(){
		log.info("Received request to get plan names");
		List<String> planNames = service.getUniquePlanNames();
		log.debug("Retrieved plan names {}", planNames);
		return new ResponseEntity<>(planNames,HttpStatus.OK);
	}
	
	@GetMapping("/status")
	public ResponseEntity<List<String>> getPlanStatus(){
		log.info("Received request to get plan status");
		List<String> planStatus  = service.getUniquePlanStatus();
		log.debug("Retrieved plan status {}",planStatus);
		return new ResponseEntity<>(planStatus,HttpStatus.OK);
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest request){
		log.info("Requesting to provide the list of data with dynamically entered data in request");
		List<SearchResponse> search = service.search(request);
		log.debug("Size of the results {}",search.size());
		return new ResponseEntity<>(search, HttpStatus.OK);
	}
	
	@GetMapping("/excel")
	public ResponseEntity<byte[]> excelReport() throws ExcelCustomExceptions{
		log.info("Requesting for the data in excel format");
		byte[] generateExcel = service.generateExcel();
		log.debug("Generated excel file successfully");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		headers.setContentDispositionFormData("attachment", "data.xlsx");
		return ResponseEntity.ok().headers(headers).body(generateExcel);
	 
	}
	
	@GetMapping("/pdf")
	public ResponseEntity<byte[]> pdfReport()throws Exception {
		log.info("Requesting for the data in pdf format");
		byte[] generatePdf = service.generatePdf();
		log.debug("Successfully generated pdf report");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf("application/pdf"));
		headers.setContentDispositionFormData("attachment", "data.pdf");
		return ResponseEntity.ok().headers(headers).body(generatePdf);

	}
}
