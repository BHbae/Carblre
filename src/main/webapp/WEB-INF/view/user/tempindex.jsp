<%@page import="com.carblre.dto.userdto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
   <h1>Welcome!</h1>

    <%
        UserDTO principal = (UserDTO) session.getAttribute("principal");
        if (principal != null) {
    %>
        <p>안녕하세요, <%= principal.getUserName() %>님!</p>
        <p>이메일: <%= principal.getEmail() %></p>
    <%
        } else {
    %>
        <p>로그인이 필요합니다.</p>
    <%
        }
        %>
 <%@ include file="../layout/footer.jsp"%>
