package com.carblre.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MessageDTO {
	private String role;
	private String content;
}
