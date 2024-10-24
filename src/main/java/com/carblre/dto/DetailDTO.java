package com.carblre.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class DetailDTO {
    private String uploardFileName;
    private int id;
    private int nickName;
    private int status; //  1. 가해자 , 2.피해자
    private String title;
    private String content;
    private String comment;
    private String userName;
    private Timestamp createdAt;
}