<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="/css/lawyerList.css">

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
            <h2 class="prih2">변호사</h2>
                <div class="lawyer-list">
                    <c:forEach items="${lawyers}" var="lawyer">
                        <!-- Making the whole card clickable by wrapping it in an anchor tag -->
                        <a href="/lawyer/lawyerInfo/${lawyer.userId}" class="lawyer-card-link">
                            <div class="lawyer-card">
                                <div class="thumbnail-area">
                                    <img src="/image/lawyer/${lawyer.uploadProfileName}" alt="Profile Image"/>
                                </div>
                                <p><strong>${lawyer.userName}</strong> 변호사</p>
                                <br>
                                <p>${lawyer.introduction}</p>
                                <br>
                                <p>법무법인 : ${lawyer.lawFirm}</p>
                            </div>
                        </a>
                    </c:forEach>
                </div>
        </div>
     </div>
</div>



<%@ include file="../layout/footer.jsp"%>
