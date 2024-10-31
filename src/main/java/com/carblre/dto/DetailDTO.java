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
    private int id;
    private int userId;
    private String category;
    private String title;
    private String content;
    private String uploadFileName;
    private String createdAt;
    private String nickName;
}
