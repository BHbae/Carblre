package com.carblre.repository.model;

import com.carblre.dto.userdto.LawyerSignUpDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LawyerDetail {

    private int userId;
    private String uploadProfileName;
    private String introduction ;
    private String lawFirm ;
    private String officeNum ;

    //깃푸쉬
    public LawyerSignUpDTO toLawyerDetail() {
        return LawyerSignUpDTO.builder()
                .userId(userId).introduction(introduction).uploadProfileName(uploadProfileName)
                .lawFirm(lawFirm).officeNum(officeNum)
                .build();
    }
}
