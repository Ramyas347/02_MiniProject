package com.example.jrtp.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.jrtp.entity.EligibilityDetails;
import com.example.jrtp.repo.EligibilityDetailsRepo;

@Component
public class AppRunner implements ApplicationRunner{
	
	private static final Logger log = LoggerFactory.getLogger(AppRunner.class);
	
	@Autowired
	private EligibilityDetailsRepo repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("App");
		log.info("App runner started");
		EligibilityDetails entity1 = new EligibilityDetails();	
		entity1.setEligID(1);
		entity1.setName("rmya");
		entity1.setMobile(355466575L);
		entity1.setGender('F');
		entity1.setSsn(968948993L);
		entity1.setPlanName("SNAP");
		entity1.setPlanStatus("APPROVED");
		entity1.setPlanStartDate(LocalDate.parse("2024-01-01"));
		entity1.setPlanEndDate(LocalDate.parse("2024-09-10"));
		
		EligibilityDetails entity2 = new EligibilityDetails();	
		entity2.setEligID(2);
		entity2.setName("ram");
		entity2.setMobile(355466575L);
		entity2.setGender('M');
		entity2.setSsn(968948993L);
		entity2.setPlanName("CAP");
		entity2.setPlanStatus("DENIED");
		
		EligibilityDetails entity3 = new EligibilityDetails();	
		entity3.setEligID(3);
		entity3.setName("Ryan");
		entity3.setMobile(355466575L);
		entity3.setGender('M');
		entity3.setSsn(13273812L);
		entity3.setPlanName("CCAP");
		entity3.setPlanStatus("IN PROGRESS");
		
		EligibilityDetails entity4 = new EligibilityDetails();	
		entity4.setEligID(4);
		entity4.setName("ramya");
		entity4.setMobile(355466579L);
		entity4.setGender('M');
		entity4.setSsn(968948990L);
		entity4.setPlanName("SNAP");
		entity4.setPlanStatus("APPROVED");
		entity4.setPlanStartDate(LocalDate.parse("2024-01-01"));
		entity4.setPlanEndDate(LocalDate.parse("2024-09-10"));
		
		log.info("App runner ended");
		
		List<EligibilityDetails> list = Arrays.asList(entity1,entity2,entity3,entity4);
		repo.saveAll(list);
}

}
 

	

