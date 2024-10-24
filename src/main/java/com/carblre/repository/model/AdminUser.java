package com.carblre.repository.model;

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
public class AdminUser {
	private int id;
	private String userName;
	private String nickName;
	private String password;
	private String email;
	private String phoneNum;
	private String role;
	private String site;
	private int status;
}
