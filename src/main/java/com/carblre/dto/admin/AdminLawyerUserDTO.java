package com.carblre.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LawyerUserDTO {
	private int id;
	
	private int userId;
	private String userName;
	private String nickName;
	private String password;
	private String email;
	private String phoneNum;
	private String role;
	
	private String uploardProfileName;
	private String introduction;
	private String lawFirm;
	private String officeNum;
}
