package com.carblre.repository.model;

import com.carblre.dto.userdto.SignDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    private int id;
    private String nickName;
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
