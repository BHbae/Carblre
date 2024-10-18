package com.carblre.dto;

import lombok.*;

@Data
@Builder
@ToString
public class TossResponseDTO {
    String mid; // 가맹점 ID
    String paymentKey; // 결제 키
    String orderId; // 주문 ID
    String orderName; // 주문명
    String version; // API 버전
    String type;  // 결제 유형
    String method; // 결제 방식
    String currency; // 통화 단위
    String totalAmount; // 총 금액
    String balanceAmount; // 잔액 금액
    String suppliedAmount; // 공급 금액
    String vat; // 부가세
    String status; // 결제 상태
    String requestedAt; // 승인요청 시간
    String approvedAt; // 승인 시간
    String useEscrow; // 에스크로 사용 여부
    String cultureExpense; // 문화비 지출 여부
    PaymentCardDto card;


}
