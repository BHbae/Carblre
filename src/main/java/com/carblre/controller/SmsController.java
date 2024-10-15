package com.carblre.controller;

import com.carblre.dto.SmsRequestDTO;
import com.carblre.service.SmsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {

    private SmsService smsService;

    @PostMapping("/send")
    public ResponseEntity<?> SendSMS(@RequestBody SmsRequestDTO smsRequestDTO)
    {
        smsService.SendSms(smsRequestDTO);
        return ResponseEntity.ok("문자 전송");
    }

}
