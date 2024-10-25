package com.carblre.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyCounselDTO {
    private int id;
    private int userId;
    private String reservationTime;
    private String content;
    private int lawyerId;
    private int status;
    private int userStatus;
}
