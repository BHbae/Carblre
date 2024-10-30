<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layout/header.jsp"%>

<link rel="stylesheet" href="/css/signin.css">


<div class="container">

    <!-- 유저와 변호사 선택 버튼 -->
    <button onclick="showUserLogin()">유저 로그인</button>
    <button onclick="showLawyerLogin()">변호사 로그인</button>

    <!-- 유저 로그인 폼 -->
    <div id="user-login-form" class="login-form">
        <h1>로그인</h1>
        <form action="/user/signIn" method="POST">
            <input type="text" name="nickName" placeholder="ID" required value="test1"><br>
            <input type="password" name="password" placeholder="비밀번호" required value="asd123!!"><br>
            <button  class="login-button" type="submit">로그인</button>
        </form>
    </div>


    <!-- 변호사 로그인 폼 -->
    <div id="lawyer-login-form" class="login-form">
        <h2>변호사 로그인</h2>
        <form action="/user/signIn" method="POST">
            <input type="text" name="nickName" placeholder="ID" required><br>
            <input type="password" name="password" placeholder="비밀번호" required><br>
            <button class="login-button" type="submit">로그인</button>
        </form>
    </div>
    <div class="login-side">
        <div  onclick="findId()" class="find-info">아이디찾기</div>
        <div  onclick="findPass()" class="find-info">패스워드 찾기</div>
        <a href="/user/selectSignUp" class="find-info">가입하기</a>
        <!-- <a href="/qr/login">QR로그인</a> -->
    </div>
    <p style="color:grey">----------소셜 로그인 시작----------</P>

    <div class="social">

    <!-- 카카오 로그인 버튼 -->
    <a c href="https://kauth.kakao.com/oauth/authorize?client_id=74ae415425bf1b53dc8f8dcf38efc2d4&redirect_uri=http://localhost:8080/user/kakao&response_type=code">
        <img src="/image/kakao.png" alt="카카오 로그인 버튼"   lass="image-link" >
    </a>
    <!-- 네이버 로그인 버튼 -->
    <a  href="/user/naver-login">
        <img src="/image/naver.png" alt="네이버 로그인 버튼"  class="image-link">
    </a>
    <!-- 구글 로그인 버튼 -->
	<a  href="https://accounts.google.com/o/oauth2/v2/auth?client_id=638590374988-00j27fgspf85dgtbau9i1c5svh8ciutu.apps.googleusercontent.com&redirect_uri=http://localhost:8080/user/google&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile">
	    <img src="/image/google.png" alt="구글 로그인 버튼" class="image-link google" >
	</a>
    </div>



</div>
<script type="text/javascript">

// 유저 로그인 폼 표시
function showUserLogin() {
    document.getElementById('user-login-form').style.display = 'block';
    document.getElementById('lawyer-login-form').style.display = 'none';
}

// 변호사 로그인 폼 표시
function showLawyerLogin() {
    document.getElementById('user-login-form').style.display = 'none';
    document.getElementById('lawyer-login-form').style.display = 'block';
}

// 페이지 이동
function findId()
{
    window.open("/user/findId", "popupWindow", "width=400,height=500")
}

function findPass()
{
    window.open("/user/findPass", "popupWindow", "width=400,height=500")
}



    // alertMessage가 있을 때만 alert 띄우기
    <c:if test="${not empty alertMessage}">
        var message = "${alertMessage}";
        alert(message);
    </c:if>


</script>
<%@ include file="../layout/footer.jsp" %>