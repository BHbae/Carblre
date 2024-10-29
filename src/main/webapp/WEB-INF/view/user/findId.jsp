<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>아이디 찾기</title>
</head>
<body>

    <form  onsubmit="handleSubmit(event)">
        <!-- 이메일 입력란 -->
        <div class="form-group">
            <label for="email">이메일</label>
            <input type="email" class="form-control" id="email" placeholder="example@carblre.com" name="email" value="" required>
        </div>

        <!-- 이메일 인증코드 발송 -->
       <div class="form-group">
            <button type="button" class="check--btn" id="emailCode" onclick="sendValidate()">발송</button>
            <button type="button" class="check--btn" id="validate" onclick="checkValidate()"
                    disabled="disabled" style="cursor: pointer;">인증 확인
            </button>
        </div>
        <button type="submit" id="findId" disabled>아이디 찾기</button>
    </form>

<div id="result"></div>


   <script>
      function sendValidate() {
           const email = document.getElementById('email').value;
           console.log('Email : ' + email);

           fetch('http://localhost:8080/send-mail/getId?email=' + email)
               .then(response => {
                   if (!response.ok) {
                       return response.json().then(data => {
                           throw new Error(data.message || '알 수 없는 에러가 발생했습니다.');
                       });
                   }
                   // 응답을 JSON 형식으로 변환
                   return response.json();
               })
               .then(data => {
                   // 서버로부터 받은 응답 데이터를 처리
                   console.log('Success:', data);
                   // EmailController에서 보낸 response를 alert으로 표시
                   alert(data.message);
                   const sendBtn = document.getElementById('emailCode');
                   const findIdBtn = document.getElementById('findId');
                   const checkValidate = document.getElementById('validate');
                   findIdBtn.disabled = false;
                   sendBtn.disabled = true;
                   checkValidate.disabled = false;
               })
               .catch(error => {
                   console.log('Error:', error);
                   // 에러 메시지를 alert으로 표시
                   alert(error.message);
               });
       }

       function checkValidate() {
           fetch('http://localhost:8080/send-mail/validate')
               .then(response => {
                   if (!response.ok) {
                       return response.json().then(data => {
                           throw new Error(data.message || '알 수 없는 에러가 발생했습니다.');
                       });
                   }
                   return response.json();
               })
               .then(data => {
                   console.log('Success:', data);
                   alert(data.message);
                   const findIdBtn = document.getElementById('findId');
                   const emailInput = document.getElementById('email');
                   const validateBtn = document.getElementById('validate');

                   findIdBtn.disabled = false;
                   validateBtn.disabled = true;
                   emailInput.readOnly = true;
               })
               .catch(error => {
                   console.log('Error:', error);
                   alert(error.message);
               });
       }


    function handleSubmit(event) {
        event.preventDefault();  // 기본 폼 제출 방지
        const email = document.getElementById('email').value;

        fetch('/user/email?email=' + email)
            .then(response => {
                if (!response.ok) {
                    return response.json().then(data => {
                        throw new Error(data.message || '알 수 없는 에러가 발생했습니다.');
                    });
                }
                return response.json();
            })
            .then(data => {
                console.log('Received data:', data);

                let userEmail = data.email || "이메일 없음";
                let userName = data.userName || "이름 없음";
                let userNickName = data.nickName || "닉네임 없음";

                const resultElement = document.getElementById('result');
                if (!resultElement) {
                    console.error('result 요소를 찾을 수 없습니다.');
                    return;
                }

                if (userEmail === "이메일 없음") {
                    alert("일치하는 아이디가 없습니다");
                    location.reload();  // 페이지 새로고침
                } else {
                    resultElement.innerHTML = `
                        <p>찾은 이메일: \${userEmail}</p>
                        <p>사용자 이름: \${userName}</p>
                        <p>닉네임: \${userNickName}</p>
                    `;
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert("오류가 발생했습니다: " + error.message);
            });
    }
    </script>
</body>
</html>
