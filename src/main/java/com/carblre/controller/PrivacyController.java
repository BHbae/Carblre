package com.carblre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/privacy")
public class PrivacyController {

    /**
     * 면책공고
     * @return
     */
    @GetMapping("/disclaimer")
    public String disclaimer(){
        return "privacy/disclaimer";
    }

    /**
     * 유한책임
     * @return
     */
    @GetMapping("/liability")
    public String liability(){
        return "privacy/liability";
    }

    /**
     * 개인정보처리방침
     * @return
     */
    @GetMapping("/policy")
    public String policy(){
        return "privacy/policy";
    }

    /**
     * 이메일무단수집거부
     * @return
     */
    @GetMapping("/refusal")
    public String refusal(){
        return "privacy/refusal";
    }

}
