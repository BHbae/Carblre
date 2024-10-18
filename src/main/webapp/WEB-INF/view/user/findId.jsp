<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <title>아이디 찾기</title>
</head>
<body>

    <form action="/user/email5" method="get" onsubmit="handleSubmit(event)">
        <!-- 이메일 입력란 -->
        <div class="form-group">
            <label for="email">이메일</label>
            <input type="email" class="form-control" id="email" placeholder="example@carblre.com" name="email" value="" required>
        </div>

        <!-- 이메일 인증코드 발송 -->
     <!--   <div class="form-group">
            <button type="button" class="check--btn" id="emailCode" onclick="sendValidate()">발송</button>
            <button type="button" class="check--btn" id="checkValidate" onclick="checkValidate()"
                    disabled="disabled" style="cursor: pointer;">인증 확인
            </button>
        </div> -->
        <button type="submit">아이디 찾기</button>
    </form>



   <script>
        function handleSubmit(event) {

             const email = document.getElementById('email').value;
            event.preventDefault(); // 기본 폼 제출 방지

            // Fetch API를 사용하여 폼 데이터를 전송합니다.
            fetch('/user/findId/email?email='+email)
            .then(response => response.json())
            .then(data => {
                // userId가 없을 때 경고창 띄우기
                if (data.message) {
                    alert("찾을수없습니다"); // 아이디를 찾을 수 없을 때
                } else {
                    innerHTML="

                    "
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }
    </script>
</body>
</html>
