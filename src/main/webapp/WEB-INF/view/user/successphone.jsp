<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

<h2>로그인 처리 중...</h2>

<script type="text/javascript">
    // WebSocket 연결
    let socket = new WebSocket("ws://192.168.0.13:8080/ws");

    socket.onopen = function(event) {
        console.log("WebSocket connection established");
    };

    socket.onmessage = function(event) {
        console.log("Received WebSocket message: ", event.data); // 메시지 확인
        if (event.data === "login_success") {
            window.location.href = "/user/signIn/tempindex";
        }
    };

    socket.onerror = function(error) {
        console.error("WebSocket error: ", error);
    };
</script>


</body>
</html>
