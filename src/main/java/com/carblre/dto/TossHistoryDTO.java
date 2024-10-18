package com.carblre.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class TossHistoryDTO {

    private int id;
    private int userId;
    private String paymentKey; // 결제 키
    private String orderId; // 주문 ID
    private String orderName; // 주문명
    private String method; // 결제 방식
    private String amount; // 금액
    private String requestedAt; // 승인요청시간
    private String approvedAt; // 승인시간

}
