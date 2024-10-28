package com.carblre.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChoiceDTO {
	 private int index;
	    private MessageDTO message;
	    private Object logprobs; // null을 허용하기 때문에 Object로 설정
	    private String finishReason;
}