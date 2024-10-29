<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="wrap">
    <!-- 상단 이미지 & 서브 페이지 이동 START -->
    <section class="sub--section">
        <div class="section--background" id="lawyer--background">
            <div class="inner--container">
                <sub class="eng">Lawyer</sub>
                <h1 class="sub--title">변호사</h1>
            </div>
        </div>
        <div style="border-bottom: 1px solid #bababa;">
            <div class="inner--container">
                <ul class="sub--top--menu">
                    <li><a href="/board/boardList">의뢰자</a></li>
                    <li><a href="/aiounseling">AI 간편상담</a></li>
                    <li class="subtop--active"><a href="/lawyer/lawyers">변호사</a></li>
                    <li><a href="/notice/notice">공지사항</a></li>
                    <li><a href="/cs/cs">고객센터</a></li>
                </ul>
            </div>
        </div>
    </section>
    <!-- 상단 이미지 & 서브 페이지 이동 END -->

<div class="inner--container">
    <div class="sub--content">
        <h2 class="prih2">변호사 정보</h2>
        <div class="lawyer-details">
            <img src="/image/lawyer/${lawyer.uploadProfileName}" alt="Profile Image" class="lawyer-image"/>
            <div class="lawyer-info">
                <p class="lawyer-name"><strong>${lawyer.userName}</strong> 변호사</p>
                <br>
                <p class="lawyer-Introduction eng"><strong>Introduction</strong><p>
                 <p>${lawyer.introduction}</p>
                 <br>
                 <br>
                <p><strong>법무법인 :</strong> ${lawyer.lawFirm}</p>
                <p><strong>사무실 전화 :</strong> ${lawyer.officeNum}</p>
                <br>
                <button class="appointment-button">상담예약</button>
            </div>
        </div>
    </div>
</div>


</div>


<%@ include file="../layout/footer.jsp"%>