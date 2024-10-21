<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 찾기</title>
</head>
<body>
<c:choose>
    <%-- userId가 없음 --%>
    <c:when test="${empty UserId}">
        <p>일치하는 계정이 없습니다.</p>
        <button onclick="history.back()">뒤로가기</button>
    </c:when>

    <c:otherwise>
    <form  onsubmit="handleSubmit(event)">
        <%-- password 입력란 --%>
        <div class="form-group">
            <label for="nickName">변경할 비밀번호</label>
        <input type="password" class="form-control" id="changePassword" placeholder="비밀번호 입력" oninput="checkPasswordMatch()" required>
        </div>
                <%-- password 입력란 --%>

        <div class="form-group">
            <label for="email">비밀번호 확인</label>
        <input type="password" class="form-control" id="checkPassword" placeholder="비밀번호 확인" oninput="checkPasswordMatch()" required>
    </div>
        </div>

    <input type="hidden" id="id" name="id" value="${UserId}">

            <p id="passwordMessage"></p>

        <button type="submit" disabled>비밀번호 변경</button>
    </form>

        <p id="message"></p>
    </c:otherwise>
</c:choose>

   <script>

    // 비밀번호 확인 함수
    function checkPasswordMatch() {
        const changedPassword = document.getElementById('changePassword').value;
        const checkedPassword = document.getElementById('checkPassword').value;
        const messageElement = document.getElementById('passwordMessage');  // 메시지를 표시할 요소
        const submitButton = document.querySelector('button[type="submit"]');  // 제출 버튼

        // 콘솔 로그로 값이 제대로 들어오는지 확인
        console.log("changedPassword:", changedPassword);
        console.log("checkedPassword:", checkedPassword);

        if (changedPassword !== checkedPassword) {
            messageElement.textContent = "비밀번호가 일치하지 않습니다.";
            messageElement.style.color = "red";  // 일치하지 않을 경우 경고 메시지 색상 변경
            submitButton.disabled = true;  // 비밀번호가 일치하지 않으면 버튼 비활성화
        } else {
            messageElement.textContent = "비밀번호가 일치합니다.";
            messageElement.style.color = "green";  // 일치할 경우 메시지 색상 변경
            submitButton.disabled = false;  // 비밀번호가 일치하면 버튼 활성화
        }
    }


 function handleSubmit(event) {
     const changedPassword = document.getElementById('changePassword').value;
     const checkedPassword = document.getElementById('checkPassword').value;
    const id = document.getElementById('id').value;
    try {
        const requestData : {
            "changedPassword" : changedPassword,
            "checkedPassword" : checkedPassword,
            "id" : id
        }

        fetch('/user/updatePass', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'  // JSON 형식으로 전송
            },
            body: JSON.stringify(requestData)  // 비밀번호를 Request Body에 담아서 전송
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log(data.message);  // console.log 오타 수정
            alert(data.message);
        })
        .catch(error => {
            console.log("catch부분에서 실패로 받음");  // console.log 오타 수정
        });

}




    </script>
</body>
</html>
