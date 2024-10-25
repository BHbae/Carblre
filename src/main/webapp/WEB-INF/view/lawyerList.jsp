<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>

<title>변호사 리스트</title>

<div class="container">
    <h2>변호사 리스트</h2>
    <div class="lawyer-list">
        <c:forEach items="${lawyers}" var="lawyer">
            <div class="lawyer-card">
                <img src="${lawyer.uploadProfileName}" alt="Profile Image" />
                <p>${lawyer.introduction}</p>
                <p><strong>법무법인:</strong> ${lawyer.lawFirm}</p>
                <p><strong>사무실 전화:</strong> ${lawyer.officeNum}</p>
            </div>
        </c:forEach>
    </div>
</div>

<%@ include file="layout/footer.jsp"%>
