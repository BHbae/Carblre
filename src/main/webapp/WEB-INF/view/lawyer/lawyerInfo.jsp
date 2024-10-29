<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="/css/lawyerInfo.css">

<div class="wrap">
    <!-- 상단 이미지 & 서브 페이지 이동 START -->
    <section class="sub--section">
        <div class="section--background" id="notice--background">
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
            <h2 class="prih2"> 변호사 정보 </h2>

                <div class="lawyer-details">
                    <img src="/image/lawyer/${lawyer.uploadProfileName}" alt="Profile Image" style="width: 20%; height: auto;" />
                    <p>${lawyer.userName} <strong> 변호사</strong></p>
                    <p><strong>소개:</strong> ${lawyer.introduction}</p>
                    <p><strong>법무법인:</strong> ${lawyer.lawFirm}</p>
                    <p><strong>사무실 전화:</strong> ${lawyer.officeNum}</p>
                </div>
                    <button>상담예약</button>
     </div>
     </div>
</div>


<%@ include file="../layout/footer.jsp"%>