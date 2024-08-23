package com.example.jrtp;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import com.example.jrtp.entity.EligibilityDetails;
import com.example.jrtp.repo.EligibilityDetailsRepo;
import com.example.jrtp.request.SearchRequest;
import com.example.jrtp.response.SearchResponse;
import com.example.jrtp.service.ReportsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

	@Mock
	private EligibilityDetailsRepo eligRepoMock;
	
	@InjectMocks
	private ReportsServiceImpl serviceImpl;
	
	@Test
	public void testGetUniquePlanNames() {
		List<String> mockPlanNames = Arrays.asList("SNAP","CAP","CCAP");
		when(eligRepoMock.findPlanNames()).thenReturn(mockPlanNames);
		
		List<String> planNames = serviceImpl.getUniquePlanNames();
		
		verify(eligRepoMock).findPlanNames();
		
		assertEquals(mockPlanNames,planNames);
		
	}
	
	@Test
	public void testGetUniquePlanStatus() {
		List<String> mockPlanStatus = Arrays.asList("APPROVED","DENIED","IN PROGRESS");
		when(eligRepoMock.findPlanStatus()).thenReturn(mockPlanStatus);
		
		List<String> planStatus = serviceImpl.getUniquePlanStatus();
		
		verify(eligRepoMock).findPlanStatus();
		
		assertEquals(mockPlanStatus,planStatus);
		
	}
	
	@Test
	public void testGetSearch() {
		
		SearchRequest request = new SearchRequest();
		request.setPlanName("SNAP");
		request.setPlanStatus("APPROVED");
		request.setPlanStartDate("2024-01-01");
		request.setPlanEndDate("2024-09-10");
		
		List<EligibilityDetails> mockEntities = new ArrayList<>();
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
		mockEntities.add(entity1);
		
	
		
		when(eligRepoMock.findAll(any(Example.class))).thenReturn(mockEntities);
		
		List<SearchResponse> search = serviceImpl.search(request);
		assertEquals(1,search.size());
		
		SearchResponse response = search.get(0);
	
		assertEquals("rmya",response.getName());
		assertEquals(355466575,response.getMobile());
		assertEquals(null,response.getEmail());
		assertEquals('F',response.getGender());
		assertEquals(968948993,response.getSsn());
		assertEquals("SNAP",response.getPlanName());
		assertEquals("APPROVED",response.getPlanStatus());
		
	}
	
	public void testGetExcel() {
		List<EligibilityDetails> entities = new ArrayList<>();
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
		entities.add(entity1);
		
		EligibilityDetails entity2 = new EligibilityDetails();	
		entity2.setEligID(2);
		entity2.setName("ram");
		entity2.setMobile(355466575L);
		entity2.setGender('M');
		entity2.setSsn(968948993L);
		entity2.setPlanName("CAP");
		entity2.setPlanStatus("DENIED");
		entity2.setPlanStartDate(LocalDate.parse("2024-01-10"));
		entity2.setPlanEndDate(LocalDate.parse("2024-11-10"));
		entities.add(entity2);
		
		when(eligRepoMock.findAll()).thenReturn(entities);
	}
}
