package com.carblre.controller;

import com.carblre.dto.TossResponseDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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

    public static final String PRINCIPAL = "principal";

    /**
     * 결제창으로 이동
     *
     * @return
     */
    @GetMapping("/payment")
    public String tosspay(Model model) {
        // 테스트용 임시값 ( @RequestParam 이용시 , 임시값 지워도됨 )

        UserDTO principal = (UserDTO) session.getAttribute("principal"); // 유저 세션 가져옴
        TossResponseDTO responseDTO = (TossResponseDTO) session.getAttribute("responseDTO");


        // principal이 null인지 체크
        if (principal == null) {
            return "redirect:/user/signIn"; // 로그인이 필요하다면 로그인 페이지로 리디렉션
        }

        int amount = 10000; //String amount = responseDTO.getTotalAmount();
        String orderId = UUID.randomUUID().toString();
        String orderName = "상품"; // String orderName = responseDTO.getOrderName();
        String customerName = principal.getUserName();
        model.addAttribute("amount", amount);
        model.addAttribute("orderId", orderId);
        model.addAttribute("orderName", orderName);
        model.addAttribute("customerName", customerName);


        System.out.println("payController /toss : " + principal);

        session.setAttribute(PRINCIPAL, principal);

        return "payment";
    }


    /**
     * 결제 성공
     *
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
                          @SessionAttribute(name = "principal") UserDTO principal) throws IOException, InterruptedException {

        System.out.println("orderId : " + orderId);
        System.out.println("paymentKey : " + paymentKey);
        System.out.println("amount : " + amount);

        RestTemplate restTemplate = new RestTemplate();


        // 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + Base64.getEncoder().encodeToString(("test_sk_Gv6LjeKD8aPB12aJqQeerwYxAdXy" + ":").getBytes()));
        headers.add("Content-Type", "application/json");

        // 바디
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("paymentKey", paymentKey);
        requestBody.put("orderId", orderId);
        requestBody.put("amount", amount);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<TossResponseDTO> response = restTemplate.exchange(
                    "https://api.tosspayments.com/v1/payments/confirm",
                    HttpMethod.POST, requestEntity, TossResponseDTO.class);

            TossResponseDTO response2 = response.getBody();
            service.insertTossHistory(response2, principal.getId());
        } catch (HttpClientErrorException e) {
            System.err.println("Error status code: " + e.getStatusCode());
            System.err.println("Error response body: " + e.getResponseBodyAsString());
            System.err.println("Response headers: " + e.getResponseHeaders());
        }

        //TODO! 성공 페이지 만들필요없이 , 바로 결제 내역 페이지로 이동
        return "success";
    }


}


