package com.carblre.repository;

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
    private String gender;


}
