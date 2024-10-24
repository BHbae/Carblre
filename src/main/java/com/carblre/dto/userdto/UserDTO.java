package com.carblre.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private int id;
    private String userName;
    private String nickName;
    private String password;
    private String email;
    private String phoneNum;
    private String role;
    private String site;
    
}
