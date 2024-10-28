package com.carblre.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ContentDTO {
	
	private String id;
	
	@SerializedName("fault_ratio")
	private FaultRatioDTO faultRatio;
	
}
