package com.carblre.dto;

import com.carblre.repository.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDTO {

    private String userName;
    private String nickName;
    private String password;
    private String email;
    private String phoneNum;
    private String role;

    public User toUser()
    {
        return User.builder()
                .userName(userName)
                .nickName(nickName)
                .password(password)
                .email(email)
                .phoneNum(phoneNum)
                .role(role)
                .build();
    }
}
