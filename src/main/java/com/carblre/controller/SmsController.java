package com.carblre.controller;

import com.carblre.dto.SmsRequestDTO;
import com.carblre.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping("/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @GetMapping("/certifyPage")
    public String certificationPage()
    {
        return "/user/certifyPage";
    }


    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendSms(@RequestBody SmsRequestDTO smsRequestDTO) {
        // SMS 전송 서비스 호출

        Map<String, String> responseMessage = new HashMap<>();

        if (smsRequestDTO.getPhoneNumber().trim().isEmpty())
        {
            responseMessage.put("error", "전화번호를 입력해주세요.");
            return ResponseEntity.badRequest().body(responseMessage);
        }

        System.out.println(smsRequestDTO.getPhoneNumber());
        smsRequestDTO.setPhoneNumber(smsRequestDTO.getPhoneNumber().trim());
        smsService.sendSms(smsRequestDTO);

        responseMessage.put("message", "SMS 발송에 성공하였습니다.");
        return ResponseEntity.ok(responseMessage);
    }

    @PostMapping("/certify")
    public ResponseEntity<Map<String, String>> certifyCode(@RequestBody SmsRequestDTO smsRequestDTO)
    {
        Map<String, String> responseMessage = new HashMap<>();
        String userInputCode = smsRequestDTO.getUserInputCode();
        String certificationCode = smsRequestDTO.getCertificationCode();

        if(userInputCode.equals(certificationCode) || !userInputCode.trim().isEmpty())
        {
            responseMessage.put("successMessage", "true");
            System.out.println("TRUETRUE");
        }
        else
        {
            System.out.println("FALSEFALSE");
            responseMessage.put("successMessage", "false");
        }

        return ResponseEntity.ok(responseMessage);
    }
}