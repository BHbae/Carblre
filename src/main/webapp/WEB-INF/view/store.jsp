<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>결제 페이지</title>
</head>
<body>
    <h1>결제 정보</h1>
    <form action="payment.jsp" method="get">
        <input type="hidden" name="amount" value= ${amount};/>
        <input type="hidden" name="orderName" value= "${orderName}" />
        <input type="hidden" name="customerName" value= "${customerName}" />

        <h2>주문 이름: "${orderName}"</h2>
        <h3>주문 ID: "${orderId}"</h3>
        <h3>고객 이름: "${customerName}"</h3>
        <h3>결제 금액: ${amount} 원</h3>

        <button type="submit">결제하기</button>
    </form>
</body>
</html>