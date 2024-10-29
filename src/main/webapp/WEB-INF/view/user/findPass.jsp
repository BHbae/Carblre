<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>아이디 찾기</title>
</head>
<body>

    <form  method="get" action="/user/emailNick">
        <!-- 닉네임 입력란 -->
        <div class="form-group">
            <label for="nickName">아이디</label>
            <input type="text" class="form-control" id="nickName" placeholder="input id" name="nickName" value="" required>
        </div>
                <!-- 이메일 입력란 -->
        <div class="form-group">
            <label for="email">이메일</label>
            <input type="email" class="form-control" id="email" placeholder="example@carblre.com" name="email" value="" required>
        </div>
        <button type="submit">비밀번호 찾기</button>
    </form>
</body>
</html>
