<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
    <h1>회원가입</h1>

    <form action="/user/signup" method="POST">
        <!-- 사용자 이름 -->
        <label for="userName">이름:</label>
        <input type="text" id="userName" name="userName" required>
        <br>

        <!-- 닉네임 -->
        <label for="nickName">닉네임:</label>
        <input type="text" id="nickName" name="nickName" required>
        <br>

        <!-- 비밀번호 -->
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" required>
        <br>

        <!-- 이메일 -->
        <label for="email">이메일:</label>
        <input type="email" id="email" name="email" required>
        <br>

        <!-- 전화번호 -->
        <label for="phoneNum">전화번호:</label>
        <input type="tel" id="phoneNum" name="phoneNum" required>
        <br>

        <!-- 제출 버튼 -->
        <button type="submit">가입하기</button>
    </form>
</div>

<%@ include file="../layout/footer.jsp" %>
