<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>

<title>변호사 리스트</title>

<div class="container">
    <h2>변호사 리스트</h2>
    <div class="lawyer-list">
        <c:forEach items="${lawyers}" var="lawyer">
            <div class="lawyer-card">
                <img src="/image/lawyer/${lawyer.uploadProfileName}" alt="Profile Image"
                style = "width : 20%; height :auto;"/>
                <p>${lawyer.introduction}</p>
                <p> ${lawyer.userName}<strong> 변호사</strong></p>
                <p><strong>법무법인:</strong> ${lawyer.lawFirm}</p>

                 <button onclick="location.href='/lawyer/lawyerInfo/${lawyer.userId}'">상세보기</button>

            </div>
        </c:forEach>
    </div>
</div>

<%@ include file="layout/footer.jsp"%>
