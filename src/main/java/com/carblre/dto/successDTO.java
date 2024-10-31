package com.carblre.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class successDTO {
	
	private int lawyerId;
	private String date;
	private int startTime;
	private int endTime;
	private String content;

}
