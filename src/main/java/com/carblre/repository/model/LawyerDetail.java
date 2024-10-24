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

    private int UserId;
    private String uploardProfileName;
    private String introduction ;
    private String lawFirm ;
    private String officeNum ;

    //깃푸쉬
    public LawyerSignUpDTO toLawyerDetail() {
        return LawyerSignUpDTO.builder()
                .UserId(UserId).introduction(introduction).uploardProfileName(uploardProfileName)
                .lawFirm(lawFirm).officeNum(officeNum)
                .build();
    }
}
