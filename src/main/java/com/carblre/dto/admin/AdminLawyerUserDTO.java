package com.carblre.dto.admin;

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
public class AdminLawyerUserDTO {
	private int id;

	private int userId;
	private String userName;
	private String nickName;
	private String password;
	private String email;
	private String phoneNum;
	private String lawFirm;
	private String role;
	private int status;

	private String originProfileName;
	private String uploadProfileName;
	private String originLicenseName;
	private String uploadLicenseName;
	private String introduction;
	private String officeNum;
	private String counselingAmount;
}
