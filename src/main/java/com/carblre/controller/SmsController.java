package com.carblre.controller;

import com.carblre.dto.SmsRequestDTO;
import com.carblre.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/send")
    public void sendSms(@RequestBody SmsRequestDTO smsRequestDTO) {
        // SMS 전송 서비스 호출
        System.out.println(smsRequestDTO.getPhoneNumber());
        smsRequestDTO.setPhoneNumber("01074418574");
        smsService.sendSms(smsRequestDTO);
    }
}