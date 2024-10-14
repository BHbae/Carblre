<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>결제하기</title>
<!-- 토스페이먼츠 결제창 SDK 추가 -->
<script src="https://js.tosspayments.com/v1/payment"></script>
</head>
<body>
<script>
    // ------ 클라이언트 키로 객체 초기화 ------
    var clientKey = 'test_ck_4yKeq5bgrpz5kx50PN943GX0lzW6';
    var tossPayments = TossPayments(clientKey);

    // 서버에서 전달받은 데이터
    var amount = 10000; // 결제 금액 (숫자형)
    var orderId = "order_12345"; // 주문 ID (문자열형)
    var orderName = "상품"; // 주문명 (문자열형)
    var customerName = "피해자"; // 구매자 이름 (문자열형)

    // ------ 결제창 띄우기 ------
    tossPayments.requestPayment('간편결제', { // 결제수단 파라미터
      amount: amount, // 결제 금액
      orderId: orderId, // 주문 ID
      orderName: orderName, // 주문명
      customerName: customerName, // 구매자 이름
      successUrl: 'http://localhost:8080/api/v1/payments/toss/success', // 결제 성공 시 이동할 페이지
      failUrl: 'http://localhost:8080/api/v1/payments/toss/fail', // 결제 실패 시 이동할 페이지
    })
    .catch(function (error) {
      if (error.code === 'USER_CANCEL') {
        console.error('User canceled payment:', error);
      } else if (error.code === 'INVALID_CARD_COMPANY') {
        console.error('Invalid card company:', error);
      } else {
        console.error('Payment error:', error);
      }
    });
</script>
</body>
</html>
