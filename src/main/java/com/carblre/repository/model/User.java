package com.carblre.repository.model;

import com.carblre.dto.userdto.SocialSignUpDTO;
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
    private String site;
    private int status;


    public SocialSignUpDTO toSign() {
	     return	SocialSignUpDTO.builder()
    	.email(email)
    	.nickName(nickName)
    	.userName(userName)
    	.role(role)
    	.phoneNum(phoneNum)
    	.password(password)
        .role(role)
         .site(site)
         .status(status)
    	.build();

    }
}
