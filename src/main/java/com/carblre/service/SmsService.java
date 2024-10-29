package com.carblre.service;

import com.carblre.dto.SmsRequestDTO;
import com.carblre.util.SmsCertificationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final SmsCertificationUtil smsCertificationUtil;

    public void sendSms(SmsRequestDTO smsRequestDTO) {
        // SMS 전송 요청에 포함된 전화번호와 인증 코드를 사용
        String phoneNumber = smsRequestDTO.getPhoneNumber(); // 요청에서 전화번호 가져오기
        System.out.println("Phone Number : " + phoneNumber);
        String certificationCode = Integer.toString((int)(Math.random() * (999999 - 100000 + 1)) + 100000);
        smsRequestDTO.setCertificationCode(certificationCode);
        // SMS 전송
        smsCertificationUtil.sendSMS(phoneNumber, "인증번호는 " + certificationCode + "입니다."); // 메시지에 인증 코드 포함
    }
}