package com.example.jrtp.request;

import lombok.Data;

@Data
public class SearchRequest {
	private String planName;
	private String planStatus;
	private String planStartDate;
	private String planEndDate;
}
