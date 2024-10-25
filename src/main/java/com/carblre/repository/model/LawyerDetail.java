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
    private String introduction ;
    private String lawFirm ;
    private String officeNum ;
    private String uploadProfileName; //변경
    private String getProfileName;// 원본이름

    private String uploadLicenseName ;// 변경자격증이름
    private String getLicenseName;// 원본

    private int counselingAmount;


    //깃푸쉬
    public LawyerSignUpDTO toLawyerDetail() {
        return LawyerSignUpDTO.builder()
                .userId(userId).introduction(introduction).uploadProfileName(uploadProfileName).getProfileName(getProfileName)
                .lawFirm(lawFirm).officeNum(officeNum).uploadLicenseName(uploadLicenseName).getLicenseName(getLicenseName)
                .counselingAmount(counselingAmount).build();
    }
}
