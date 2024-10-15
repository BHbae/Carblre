<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="/css/signin.css">
    <div class="container">
<h1>로그인</h1>

    <!-- 일반 로그인 폼 -->
    <div class-"login-form">
    <form action="/user/signin" method="POST">
        <label for="nickName">닉네임:</label>
        <input type="text" id="nickName" name="nickName" required>
        <br>
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <button type="submit">로그인</button>
    </form>
    </div>
    <a href="/user/signin">아이디 찾기</a>
    <a href="/user/signup">비밀번호 찾기</a>
    <a href="/user/signup">가입하기</a>


    <div class="social">

    <!-- 카카오 로그인 버튼 -->
    <a href="https://kauth.kakao.com/oauth/authorize?client_id=74ae415425bf1b53dc8f8dcf38efc2d4&redirect_uri=http://localhost:8080/user/kakao&response_type=code">
        <img src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png" alt="카카오 로그인 버튼">
    </a>

    <!-- 네이버 로그인 버튼 -->
    <a href="/user/naver-login">
        <img src="https://static.nid.naver.com/oauth/small_g_in.PNG" alt="네이버 로그인 버튼" style="width: 150px;height: 50px;">
    </a>

	<a href="https://accounts.google.com/o/oauth2/v2/auth?client_id=638590374988-00j27fgspf85dgtbau9i1c5svh8ciutu.apps.googleusercontent.com&redirect_uri=http://localhost:8080/user/google&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile">
	    <img src="https://developers.google.com/identity/images/btn_google_signin_light_normal_web.png" alt="구글 로그인 버튼">
	</a>
    </div>

    </div>

<%@ include file="../layout/footer.jsp" %>
