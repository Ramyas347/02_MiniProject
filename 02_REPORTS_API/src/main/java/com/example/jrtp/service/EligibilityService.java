package com.example.jrtp.service;
import java.util.List;

import com.example.jrtp.exception.ExcelCustomExceptions;
import com.example.jrtp.exception.PdfCustomExceptions;
import com.example.jrtp.request.SearchRequest;
import com.example.jrtp.response.SearchResponse;

public interface EligibilityService {

	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatus();
	
	public List<SearchResponse> search(SearchRequest request);
	
	public byte[] generateExcel() throws ExcelCustomExceptions;
	
	public byte[] generatePdf()throws PdfCustomExceptions;
	
	
}
