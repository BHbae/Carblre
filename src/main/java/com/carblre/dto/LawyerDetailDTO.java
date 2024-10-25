package com.carblre.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LawyerDetailDTO {
    private int userId;
    private String uploadProfileName;
    private String introduction ;
    private String lawFirm ;
    private String officeNum ;
    private String userName;
}
