 package com.example.employeeservice;import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EmployeeAppConfig{
	
	@Bean
	public RestTemplate rt(){
		return new RestTemplate();
		
	}
	
}