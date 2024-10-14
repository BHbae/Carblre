package com.carblre.repository;

import com.carblre.dto.TossHistoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentHistoryRepository {

    // 결제 승인
    int insertTossHistory(TossHistoryDTO historyDTO);

    // 검색
    TossHistoryDTO searchPayment(@Param("id") int id);

    // 결제취소
    int cancelPayment(@Param("paymentKey") String paymenyKey , @Param("orderId") String orderId);

    // 취소 히스토리
    int cancelPaymentHistory(TossHistoryDTO historyDTO);


}
