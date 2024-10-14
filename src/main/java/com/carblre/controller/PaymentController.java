package com.carblre.controller;


import com.carblre.dto.TossResponseDTO;
import com.carblre.repository.User;
import com.carblre.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;

@Controller
@RequestMapping("/toss")
@RequiredArgsConstructor
public class PaymentController {

    private final HttpSession session;
    private final PaymentService service;

    /**
     * 결제창으로 이동
     *
     * @return
     */
    @GetMapping("/payment")
    public String tosspay() {
//        User principal = (User) session.getAttribute("principal"); // 유저 세션 가져옴
//        System.out.println("payController /toss : " + principal);
        return "payment";
    }


    /**
     * 결제 성공
     * @param orderId
     * @param paymentKey
     * @param amount
     * @param principal
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @GetMapping("/success")
    public String success(@RequestParam(name = "orderId") String orderId,
                          @RequestParam(name = "paymentKey") String paymentKey,
                          @RequestParam(name = "amount") String amount,
                          @SessionAttribute(name = "principal")User principal) throws IOException , InterruptedException {

        System.out.println("orderId : " + orderId);
        System.out.println("paymentKey : " + paymentKey);
        System.out.println("amount : " + amount);

        RestTemplate restTemplate = new RestTemplate();

        // 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic dGVzdF9za19lcVJHZ1lPMXI1UDdFZ0RLd05KYlZRbk4yRXlhOg=="); // basic64 << 인코딩
        headers.add("Content-Type", "application/json");

        // 바디
        Map<String ,String> requestBody = new HashMap<>();
        requestBody.put("paymentKey" , paymentKey);
        requestBody.put("orderId" , orderId);
        requestBody.put("amount" ,amount);

        HttpEntity<Map<String,String>> requestEntity = new HttpEntity<>(requestBody ,headers);

        try{
            ResponseEntity<TossResponseDTO> response = restTemplate.exchange(
                    "https://api.tosspayments.com/v1/payments/confirm"
                    , HttpMethod.POST , requestEntity , TossResponseDTO.class);

            TossResponseDTO response2 = response.getBody();
            service.insertTossHistory(response2 , principal.getId());
        } catch (HttpClientErrorException e) {
            System.err.println("Error response body: " + e.getResponseBodyAsString());
        }

        return "/payment/success";

    }

    /**
     * 결제 실패
     * @return 메인페이지
     */
    @GetMapping("/fail")
    public String fail() {
        return "redirect:/";
    }

}


