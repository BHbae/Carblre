package com.carblre.controller;


import com.carblre.dto.PrincipalDTO;
import com.carblre.dto.TossResponseDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.repository.model.User;
import com.carblre.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpHeaders;

@Controller
@RequestMapping("/toss")
@RequiredArgsConstructor
public class PaymentController {

    private final HttpSession session;
    private final PaymentService service;

    public static  final String PRINCIPAL = "principal";

    /**
     * 결제창으로 이동
     *
     * @return
     */
    @GetMapping("/payment")
    public String tosspay(Model model) {
    // 테스트용 임시값 ( @RequestParam 이용시 , 임시값 지워도됨 )

    User principal = (User) session.getAttribute("principal"); // 유저 세션 가져옴

    int amount = 10000;
    String orderId = "order_12345"; // TODO! 서비스에  getOrderId() 메서드 삭제하고 아래 코드 사용해도되는지 테스트 해보기
    //String orderId = UUID.randomUUID().toString();
    String orderName = "상품";
    String customerName = "피해자";
    model.addAttribute("amount",amount);
    model.addAttribute("orderId",orderId);
    model.addAttribute("orderName",orderName);
    model.addAttribute("customerName",customerName);


        System.out.println("payController /toss : " + principal);

        session.setAttribute(PRINCIPAL, principal);

        return "payment";
    }

    @GetMapping("/store")
    public String store(Model model){

        int amount = 10000;
        String orderId = "order_12345";
        String orderName = "상품";
        String customerName = "피해자";
        model.addAttribute("amount",amount);
        model.addAttribute("orderId",orderId);
        model.addAttribute("orderName",orderName);
        model.addAttribute("customerName",customerName);

        return "store";
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

        //TODO! 성공 페이지 만들필요없이 , 바로 결제 내역 페이지로 이동
        return "success";

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


