<%@page import="com.carblre.dto.userdto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layout/header.jsp"%>
   <h1>Welcome!</h1>

<c:choose>
    <c:when test="${not empty principal}">
        <p>안녕하세요, ${principal.userName}님!</p>
        <p>이메일: ${principal.email}</p>
        <a href="/user/logout">로그아웃</a>


    </c:when>
    <c:otherwise>
        <p>로그인이 필요합니다.</p>
    </c:otherwise>
</c:choose>

 <%@ include file="../layout/footer.jsp"%>
