package com.carblre.dto.userdto;

import com.carblre.repository.model.LawyerDetail;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LawyerDetailDTO {

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
    public LawyerDetail toLawyerDetail() {
        return LawyerDetail.builder()
                .userId(userId).introduction(introduction).uploadProfileName(uploadProfileName).getProfileName(getProfileName)
                .lawFirm(lawFirm).officeNum(officeNum).uploadLicenseName(uploadLicenseName).getLicenseName(getLicenseName)
                .counselingAmount(counselingAmount).build();
    }

}
