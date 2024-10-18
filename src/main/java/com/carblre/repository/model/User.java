package com.carblre.repository.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    private int id;
    private String userId;
    private String userName;
    private String password;
    private String email;
    private String phoneNum;
    private String role;


    public SignDTO toSign() {
	     return	SignDTO.builder()
    	.email(email)
    	.nickName(nickName)
    	.userName(userName)
    	.role(role)
    	.phoneNum(phoneNum)
    	.password(password)
    	.build();

    }
}
