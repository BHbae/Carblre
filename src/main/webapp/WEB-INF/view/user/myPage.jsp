<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layout/header.jsp" %>

<div class="wrap">
    <!-- 상단 이미지 & 서브 페이지 이동 START -->
    <section class="sub--section">
        <div class="section--background" id="notice--background">
            <div class="inner--container">
                <sub class="eng">My Page</sub>
                <h1 class="sub--title">마이페이지</h1>
            </div>
        </div>
        <div style="border-bottom: 1px solid #bababa;">
            <div class="inner--container">
                <ul class="sub--top--menu">
                    <li class="subtop--active"><a href="/user/mypage">마이페이지</a></li>
                </ul>
            </div>
        </div>
    </section>
    <!-- 상단 이미지 & 서브 페이지 이동 END -->

    <div class="inner--container">
        <div class="sub--content">
            <h2 class="prih2 user--form">마이페이지</h2>

            <div class="info infoupdate">
                <div class="btn btn--wrap">
                    <div id="mypage--counsel">
                                <p class="update-info" onclick="checkUserCounsel()"> 예약 현황 </p>
                    </div>
                </div>



                <c:if test="${principal.site != '서버'}">
                    <p class="label--info">이름</p>
                    <p class="label--value">${principal.userName}</p>
                    <p class="label--info">아이디</p>
                    <p class="label--value">${principal.site}</p>
                    <p class="label--info">이메일</p>
                    <p class="label--value">${principal.email}</p>
                </c:if>
                <c:if test="${principal.site == '서버'}">
                    <p class="label--info">이름</p>
                    <p class="label--value">${principal.userName}</p>
                    <p class="label--info">아이디</p>
                    <p class="label--value">${principal.nickName}</p>
                    <p class="label--info">이메일</p>
                    <p class="label--value">${principal.email}</p>
                </c:if>
                <div class="btn btn--wrap">
                    <div id="mypage--edit">
                        <p class="update-info" onclick="infoUpdate()"> 개인정보 수정 </p>
                    </div>
                    <div id="mypage--password">
                        <p class="update-info" onclick="infoUpdatePass()"> 비밀번호 변경 </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>

    function checkUserCounsel() {
        window.location.href = "/user/checkUserCounsel";
    }

    function checkLawyerCounsel() {
        window.location.href = "/user/checkLawyerCounsel";
    }


    function infoUpdate() {
        window.location.href = "/user/infoUpdate";
    }

    function infoUpdatePass() {
        window.location.href = "/user/infoUpdatePass";
    }
</script>

<%@ include file="../layout/footer.jsp" %>