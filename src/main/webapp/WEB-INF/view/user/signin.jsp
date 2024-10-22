<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layout/header.jsp"%>

<link rel="stylesheet" href="/css/signin.css">


    <div class="container">
<h1>로그인</h1>

    <!-- 일반 로그인 폼 -->
    <div class="login">
	    <form action="/user/signIn" method="POST">
	        <label for="nickName">닉네임:</label>
	        <input type="text" id="nickName" name="nickName" required>
	        <br>
	        <label for="password">비밀번호:</label>
	        <input type="password" id="password" name="password" required>
	        <br>
	        <button type="submit">로그인</button>
	    </form>
    </div>

        <div  onclick="findId()">아이디찾기</div>
        <a href="/user/signIp">비밀번호 찾기</a>
        <a href="/user/signUp">가입하기</a>
        <!-- <a href="/qr/login">QR로그인</a> -->
        </div>


    <div class="social">

    
    <a href="/user/signUp">가입하기</a>

    <!-- 카카오 로그인 버튼 -->
    <a class="image-link" href="https://kauth.kakao.com/oauth/authorize?client_id=74ae415425bf1b53dc8f8dcf38efc2d4&redirect_uri=http://localhost:8080/user/kakao&response_type=code">
        <img src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_small.png" alt="카카오 로그인 버튼">
    </a>

    <!-- 네이버 로그인 버튼 -->
    <a class="image-link" href="/user/naver-login">
        <img src="/image/naver.png" alt="네이버 로그인 버튼" style="width: 40px;height: 40px;">
    </a>

	<a class="image-link" href="https://accounts.google.com/o/oauth2/v2/auth?client_id=638590374988-00j27fgspf85dgtbau9i1c5svh8ciutu.apps.googleusercontent.com&redirect_uri=http://localhost:8080/user/google&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile">
	    <img src="/image/google.png" alt="구글 로그인 버튼">
	</a>
    </div>

    

    
    </div>
<script type="text/javascript">

function findId()
{
    window.open("/user/findId", "popupWindow", "width=400,height=500")
}



    // alertMessage가 있을 때만 alert 띄우기
    <c:if test="${not empty alertMessage}">
        var message = "${alertMessage}";
        alert(message);
    </c:if>


</script>
<%@ include file="../layout/footer.jsp" %>
