<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 찾기</title>
    <style>
        /* 페이지 전체 고정 크기 설정 */
        body {
            align-items: center;
            justify-content: center;
            margin: 0;
            background-color: #f9f9f9;
            font-family: Arial, sans-serif;
        }

        .container {
            width: 100%;
            max-width: 400px;
            margin-left: 15px;
            background: #ffffff;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .form-group {
            margin-bottom: 1.5em;
            text-align: left;
        }

        label {
            display: block;
            font-weight: bold;
            color: #333;
        }

        input[type="email"], input[type="text"] {
            width: 100%;
            padding: 0.8em;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 1em;
            box-sizing: border-box;
            transition: border-color 0.3s;
        }

        input[type="email"]:focus, input[type="text"]:focus {
            border-color: #007bff;
            outline: none;
        }

        button[type="submit"] {
            width: 100%;
            padding: 0.8em;
            margin-top: 1em;
            font-size: 1em;
            font-weight: bold;
            color: #fff;
            background-color: #23220f;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button[type="submit"]:hover {
            background-color: #434344;
        }
    </style>
</head>
<body>
<form method="get" action="/user/emailNick">
    <div class="container">
        <h2>비밀번호 찾기</h2>
        <div class="form-group">
            <label for="nickName">아이디</label>
            <input type="text" class="form-control" id="nickName" placeholder="input id" name="nickName" value="" required>
        </div>
        <div class="form-group">
            <label for="email">이메일</label>
            <input type="email" class="form-control" id="email" placeholder="example@carblre.com" name="email" value="" required>
        </div>
        <button type="submit">비밀번호 찾기</button>
    </div>
</form>
</body>
</html>