package com.carblre.service;

import com.carblre.dto.SmsRequestDTO;
import com.carblre.util.SmsCertificationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsService {

    private SmsCertificationUtil smsCertificationUtil;

    public void SendSms(SmsRequestDTO smsRequestDTO)
    {
        String phoneNumber = smsRequestDTO.getPhoneNumber();
        String certificationCode = Integer.toString((int)(Math.random() * (999999 - 100000 + 1)) + 100000);
        smsCertificationUtil.sendSMS(phoneNumber, certificationCode);
    }

}
