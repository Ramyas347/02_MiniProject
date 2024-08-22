 package com.example.jrtp.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.jrtp.entity.EligibilityDetails;
import com.example.jrtp.exception.ExcelCustomExceptions;
import com.example.jrtp.exception.PdfCustomExceptions;
import com.example.jrtp.repo.EligibilityDetailsRepo;
import com.example.jrtp.request.SearchRequest;
import com.example.jrtp.response.SearchResponse;
import com.example.utility.ExcelGeneratorFile;
import com.example.utility.PdfGeneratorFile;

@Service
public class ReportsServiceImpl implements EligibilityService {

	private static final Logger log = LoggerFactory.getLogger(ReportsServiceImpl.class);
	
	@Autowired
	private EligibilityDetailsRepo eligRepo;
	
	@Override
	public List<String> getUniquePlanNames() {
		log.debug("Fetching unique plan names");
		List<String> planNames = eligRepo.findPlanNames();
		log.debug("Fetched plan names: {}", planNames);
		return planNames;
	}

	@Override
	public List<String> getUniquePlanStatus() {
		log.debug("Fetching unique plan statuses");
		return eligRepo.findPlanStatus();
	}

	@Override
	public List<SearchResponse> search(SearchRequest request) {
		log.debug("Performing search with request: {}",request);
		List <SearchResponse> response = new ArrayList<>();
		
		EligibilityDetails queryBuilder = new EligibilityDetails();
		
		String planName = request.getPlanName();
		
		if(planName!=null && !planName.equals("")) {
			queryBuilder.setPlanName(planName);
		}
		String planStatus = request.getPlanStatus();
		if(planStatus!=null && !planStatus.equals("")) {
			queryBuilder.setPlanStatus(planStatus);
		}
		String planStartDate = request.getPlanStartDate();
		if(planStartDate!=null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			queryBuilder.setPlanStartDate(LocalDate.parse(planStartDate,formatter));
		}
		String planEndDate = request.getPlanEndDate();
		if(planEndDate!=null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			queryBuilder.setPlanEndDate(LocalDate.parse(planEndDate,formatter));
		}
		
		log.debug("Query Builder: {}", queryBuilder);
		

		
		//Example<EligibilityDetails> example = Example.of(queryBuilder,matcher);//Generates a custom query
		
		List<EligibilityDetails> entities = eligRepo.findAll(Example.of(queryBuilder));
		
		log.debug("Retrieved {} entities from database", entities.size());
		
		for(EligibilityDetails entity:entities) {
			SearchResponse sr = new SearchResponse();
			BeanUtils.copyProperties(entity, sr);
			response.add(sr);
		}
		log.debug("Search complete. Found {} results",response.size());
		return response;
	}

	@Override
	public byte[] generateExcel() throws ExcelCustomExceptions {
		log.info("Generating Excel Report");
	    try {
	    	log.debug("Attempting to retrieve all entities from the database ");
	        List<EligibilityDetails> entities = eligRepo.findAll();
	        if(entities == null || entities.isEmpty()) {
	        	log.warn("No data found to generate excel file");
	        	throw new ExcelCustomExceptions("No data found to generate excel file");
	        }
	        log.debug("Retrieved {} entities from database", entities.size());
	        return ExcelGeneratorFile.excelFile(entities);
	    } catch (DataAccessException e) {
	    	log.error("Error occurred with database",e);
	        throw new ExcelCustomExceptions("Error occurred with database", e);
	    }catch(Exception e) {
	    	log.error("Unexpected error occurred", e);
	    	throw new ExcelCustomExceptions("Unexpected error occurred:" +e.getMessage(), e);
	    }
	}


	public byte[] generatePdf() throws PdfCustomExceptions {
		log.info("Generating pdf report");
		try{
			log.debug("Attempting to retrieve all entities from the database");
			List<EligibilityDetails> entities = eligRepo.findAll();
			if(entities == null || entities.isEmpty()) {
				log.warn("No data found to generate excel file");
				throw new PdfCustomExceptions("No data found to generate pdf file");
			}
			 log.debug("Retrieved {} entities from database", entities.size());
			return PdfGeneratorFile.generatPdfFile(entities);
		}catch(DataAccessException e) {
			log.error("Error occurred with database",e);
			throw new PdfCustomExceptions("Error occurred with database", e);
		}catch(Exception e) {
			log.error("Unexpected error occurred", e);
	    	throw new PdfCustomExceptions("Unexpected error occurred:" +e.getMessage(), e);
	    }
		
	}
	

}
