package com.carblre.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AdminTossHistoryDTO {
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
