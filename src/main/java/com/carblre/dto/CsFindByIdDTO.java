package com.carblre.dto;

import lombok.Data;

@Data
public class CsFindByIdDTO {
	
	private int id;
	private int userId;
	private String title;
	private String userName;	
	private String request;	
	private String requestTime;
	private String response;
	private String responseTime;

}
