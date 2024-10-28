package com.carblre.dto.userdto;

import com.carblre.repository.model.LawyerDetail;
import com.carblre.repository.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LawyerSignUpDTO {
    private String userName ;
    private String nickName ;
    private String password ;
    private String email  ;
    private String phoneNum ;
    private String role ;
    private String site ;
<<<<<<< HEAD
=======
    private int status;
>>>>>>> 67e32f4b04d7f2f816fba6640c8d6e066bccba6e
    private int userId;
    private String introduction  ;
    private String lawFirm ;
    private String officeNum ;
<<<<<<< HEAD
    private String uploadProfileName ;
    private MultipartFile profileImage;
    private String getProfileName;
=======
    private int counselingAmount;
>>>>>>> 67e32f4b04d7f2f816fba6640c8d6e066bccba6e


    private String uploadProfileName ;// 변경이름
    private MultipartFile profileImage; // 저장파일
    private String getProfileName;// 원본이름

    private String uploadLicenseName ;// 변경자격증이름
    private MultipartFile licenseImage; // 저장파일
    private String getLicenseName;// 원본자격증이름

    private final String UPLOAD_PROFILE_DIR = "src/main/resources/static/image/profile/";  // 파일 저장 경로
    private final String UPLOAD_LAWYER_DIR = "src/main/resources/static/image/lawyer/";  // 파일 저장 경로
    private final String URL_PROFILE_PREFIX = "/image/profile/";  // 웹에서 접근하는 경로
    private final String URL_LAWYER_PREFIX = "/image/lawyer/";  // 웹에서 접근하는 경로

    public User toUser(){
        return  User.builder()
                .userName(userName).nickName(nickName).password(password)
                .email(email).phoneNum(phoneNum).role(role).site(site).status(status)
                .build();
    }

    public LawyerDetail toLawyerDetail(){
        return  LawyerDetail.builder()
<<<<<<< HEAD
                .userId(userId).introduction(introduction).uploadProfileName(uploadProfileName)
                .lawFirm(lawFirm).officeNum(officeNum)
                .build();
=======
                .userId(userId).introduction(introduction).uploadProfileName(uploadProfileName).getProfileName(getProfileName)
                .lawFirm(lawFirm).officeNum(officeNum).uploadLicenseName(uploadLicenseName).getLicenseName(getLicenseName)
                .counselingAmount(counselingAmount).build();
>>>>>>> 67e32f4b04d7f2f816fba6640c8d6e066bccba6e
    }

    public String UUIDUploadProfileName(){

        return UUID.randomUUID().toString() + "_" + getProfileName;
    }
    public String UUIDUploardLawyerName(){

        return UUID.randomUUID().toString() + "_" + getLicenseName;
    }
}
