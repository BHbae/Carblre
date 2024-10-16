package com.carblre.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PrincipalDTO {

	private Integer id;
	private String username;
	private String userId;
	private String userSocialType;
	private String subscribing;
	private String orderName;
}
