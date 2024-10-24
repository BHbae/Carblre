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
    private int UserId;
    private String introduction  ;
    private String lawFirm ;
    private String officeNum ;
    private String uploardProfileName ;
    private MultipartFile profileImage;
    private String getProfileName;

    private final String UPLOAD_DIR = "src/main/resources/static/image/lawyer/";  // 파일 저장 경로
    private final String URL_PREFIX = "/image/lawyer/";  // 웹에서 접근하는 경로

    public User toUser(){
        return  User.builder()
                .userName(userName).nickName(nickName).password(password)
                .email(email).phoneNum(phoneNum).role(role)
                .build();
    }

    public LawyerDetail toLawyerDetail(){
        return  LawyerDetail.builder()
                .UserId(UserId).introduction(introduction).uploardProfileName(uploardProfileName)
                .lawFirm(lawFirm).officeNum(officeNum)
                .build();
    }

    public String UUIDUploardProfileName(){

        return UUID.randomUUID().toString() + "_" + getProfileName;
    }
}
