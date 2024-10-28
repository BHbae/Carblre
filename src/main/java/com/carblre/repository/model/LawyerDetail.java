package com.carblre.repository.model;

import com.carblre.dto.userdto.LawyerDetailDTO;
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
<<<<<<< HEAD
    private String uploadProfileName;
=======
>>>>>>> 67e32f4b04d7f2f816fba6640c8d6e066bccba6e
    private String introduction ;
    private String lawFirm ;
    private String officeNum ;
    private String uploadProfileName; //변경
    private String getProfileName;// 원본이름

    private String uploadLicenseName ;// 변경자격증이름
    private String getLicenseName;// 원본

    private int counselingAmount;


    //    깃푸쉬
    public LawyerSignUpDTO toLawyerSignUp() {
        return LawyerSignUpDTO.builder()
<<<<<<< HEAD
                .userId(userId).introduction(introduction).uploadProfileName(uploadProfileName)
                .lawFirm(lawFirm).officeNum(officeNum)
                .build();
=======
                .userId(userId).introduction(introduction).uploadProfileName(uploadProfileName).getProfileName(getProfileName)
                .lawFirm(lawFirm).officeNum(officeNum).uploadLicenseName(uploadLicenseName).getLicenseName(getLicenseName)
                .counselingAmount(counselingAmount).build();
    }
    private final String URL_PROFILE_PREFIX = "/image/profile/";  // 웹에서 접근하는 경로
    private final String URL_LAWYER_PREFIX = "/image/lawyer/";  // 웹에서 접근하는 경로

    public LawyerDetailDTO toLawyerDetailDTO() {
        return LawyerDetailDTO.builder()
                .userId(userId).introduction(introduction).uploadProfileName("/image/profile/"+ uploadProfileName).getProfileName(getProfileName)
                .lawFirm(lawFirm).officeNum(officeNum).uploadLicenseName("/image/lawyer/"+uploadLicenseName).getLicenseName(getLicenseName)
                .counselingAmount(counselingAmount).build();
>>>>>>> 67e32f4b04d7f2f816fba6640c8d6e066bccba6e
    }
}
