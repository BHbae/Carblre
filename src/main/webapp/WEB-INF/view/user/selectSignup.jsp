<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../layout/subHeader.jsp" %>

<div class="wrap">
    <section style="margin-bottom: 100px"></section>
    <div class="inner--container">
        <div class="sub--content">
            <h2 class="prih2 user--form">회원가입</h2>
            <div class="select-signup infoupdate">

                <div class="common--signup">
                    <div class="signup-btn" style="margin-bottom: 15px;" onclick="signUpUserId()"> 일반 가입하기</div>
                    <div class="signup-btn" onclick="signUpLawyerId()"> 변호사 가입하기</div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

    function signUpUserId() {
        window.location.href = "/user/signUp";  // window.location.href는 값으로 할당
    }

    function signUpLawyerId() {
        window.location.href = "/user/lawyerSignUp";  // window.location.href는 값으로 할당
    }
</script>

<%@ include file="../layout/footer.jsp" %>
