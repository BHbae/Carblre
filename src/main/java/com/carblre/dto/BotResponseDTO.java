package com.carblre.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;


	@Data
	@ToString
	public class BotResponseDTO {
	    private String id;
	    private String object;
	    private long created;
	    private String model;
	    private List<ChoiceDTO> choices;
	    private Usage usage;
	    private String systemFingerprint;

	    
	}



	@Data
	class Usage {
	    private int promptTokens;
	    private int completionTokens;
	    private int totalTokens;

	    
	}
