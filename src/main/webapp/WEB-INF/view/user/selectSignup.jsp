<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <style>
 .signup-btn {
     display: inline-block;
     padding: 10px 20px;
     background-color: #007BFF; /* 파란색 배경 */
     color: white; /* 글자 색상 */
     text-align: center;
     border-radius: 5px; /* 모서리 둥글게 */
     cursor: pointer; /* 마우스를 손가락 모양으로 */
     text-decoration: none; /* 밑줄 제거 */
 }

 .signup-btn:hover {
     background-color: #0056b3; /* 마우스 오버 시 배경색 변경 */
 }
p {
    font-size: 8px;
}
 </style>

<%@ include file="../layout/header.jsp"%>

    <h1> 회원가입 <h1>

        <div>

        <div class="signup-btn" onclick="signUpUserId()"> 일반 유저 가입하기</div>
        <p>~_~_~~_ 뭐시기 양식 </p>
        <div class="signup-btn" onclick="signUpLawyerId()"> 변호사 가입하기</div>
        <p style="">~_~_~~_ 뭐시기 양식
                변호사 에 대한 책임 뭐시기~~~~~~
                ~~ 확인을 위해 최대 *일의 기한이 걸립니다.
        </p>
        </div>
 <script type="text/javascript">

function signUpUserId() {
    window.location.href = "/user/signUp";  // window.location.href는 값으로 할당
}

function signUpLawyerId() {
    window.location.href = "/user/lawyerSignUp";  // window.location.href는 값으로 할당
}
 </script>
<%@ include file="../layout/footer.jsp"%>
