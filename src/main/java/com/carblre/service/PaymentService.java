package com.carblre.service;

import com.carblre.dto.TossHistoryDTO;
import com.carblre.dto.TossResponseDTO;
import com.carblre.repository.interfaces.PaymentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@Service
@RequiredArgsConstructor // Autowierd 없이 생성자 주입가능

public class PaymentService {

    private final PaymentHistoryRepository historyRepository;



    /**
     * 결제승인
     * @param responseDTO
     * @param principalId
     * @return
     */
    @Transactional
    public int insertTossHistory(TossResponseDTO responseDTO , int principalId){

        int result = 0;

        TossHistoryDTO historyDTO = TossHistoryDTO.builder()
                .paymentKey(responseDTO.getPaymentKey()).userId(principalId)
                .orderId(responseDTO.getOrderId()).orderName(responseDTO.getOrderName())
                .amount(responseDTO.getTotalAmount())
                .approvedAt(responseDTO.getApprovedAt())
                .build();

        result = historyRepository.insertTossHistory(historyDTO);

        if (result != 1) {
            throw new RuntimeException("결제 처리 실패");
        }
        return result;
    }


    /**
     * 결제 취소
     * @param id
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @Transactional
    public String cancelPayment(int id) throws IOException , InterruptedException {

        TossHistoryDTO historyDTO = historyRepository.searchPayment(id);

        String uri = "https://api.tosspayments.com/v1/payments/" + historyDTO.getPaymentKey() +"/cancel" ;
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri))
                .header("Authorization", "Basic dGVzdF9ja180eUtlcTViZ3JwejVreDUwUE45NDNHWDBselc2OnRlc3Rfc2tfR3Y2TGplS0Q4YVBCMTJhSnFRZWVyd1l4QWRYeQ==")
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"cancelReason\":\"고객이 취소를 요청함\"}"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        historyRepository.cancelPaymentHistory(historyDTO);

        historyRepository.cancelPayment(historyDTO.getPaymentKey(), historyDTO.getOrderId());

        return response.body();

    }

    /**
     * 고유 주믄Id (orderId) 생성
     * @return 랜덤
     */
    public String getOrderId() {
        return UUID.randomUUID().toString();
    }













}
