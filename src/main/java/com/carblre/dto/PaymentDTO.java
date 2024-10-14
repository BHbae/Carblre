package com.carblre.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PaymentDTO {

    private int id;
    private int userId;
    private int amount; // 금액
    private String paymentMethod; // 결제방식
    private String paymentItem; // 구매
    private String oauthCode; // 인증코드
    private Timestamp createdAt;

}
