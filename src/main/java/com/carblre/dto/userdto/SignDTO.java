package com.carblre.dto.userdto;

import com.carblre.repository.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignDTO {

    private String userName;
    private String nickName;
    private String password;
    private String email;
    private String phoneNum;
    private String role;
    private String kakaoId;

    public User toUsern() {
        return    User.builder()
                .email(email)
                .nickName(nickName)
                .userName(userName)
                .role(role)
                .phoneNum(phoneNum)
                .password(password)
                .build();

    }
}