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
                    <li class="subtop--active"><a href="/lawyer/mypage">마이페이지</a></li>
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
                       <p class="update-info" onclick="checkLawyerCounsel()"> 변호 예약 현황 </p>
                    </div>
                </div>

                    <p class="label--info">이름</p>
                    <p class="label--value">${principal.userName}</p>
                    <p class="label--info">아이디</p>
                    <p class="label--value">${principal.nickName}</p>
                    <p class="label--info">이메일</p>
                    <p class="label--value">${principal.email}</p>
                    <p class="label--info">법인명</p>
                    <p class="label--value">${lawyer.lawFirm}</p>
                    <p class="label--info">법인 전화번호</p>
                    <p class="label--value">${lawyer.officeNum}</p>
                    <p class="label--info">소개</p>
                    <p class="label--value">${lawyer.introduction}</p>
                    <p class="label--info">프로필 사진</p>
                    <div><img class="label--value" src="${lawyer.uploadProfileName}" alt="Profile Image" /></div>
                    <p class="label--info">지정 상담가격</p>
                    <p class="label--value">${lawyer.counselingAmount}</p>
                    <button onclick="amountUpdate()"> 수정하기</button>
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
        window.location.href = "/lawyer/checkLawyerCounsel";
    }


    function infoUpdate() {
        window.location.href = "/user/infoUpdate";
    }

    function infoUpdatePass() {
        window.location.href = "/user/infoUpdatePass";
    }
    function amountUpdate() {
        window.open("/lawyer/amountUpdate", "상담 가격", "width=600,height=400");
	}
</script>

<%@ include file="../layout/footer.jsp" %>