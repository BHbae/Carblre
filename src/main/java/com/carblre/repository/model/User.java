package com.carblre.repository.model;

import com.carblre.dto.userdto.SignDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
    private int id;
    private String userName;
    private String nickName;
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
