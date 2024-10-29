<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 찾기</title>
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

        <!-- 이메일 인증코드 발송 -->
       <div class="form-group">
            <button type="button" class="check--btn" id="emailCode" onclick="sendValidate()">발송</button>
            <button type="button" class="check--btn" id="validate" onclick="checkValidate()"
                    disabled="disabled" style="cursor: pointer;">인증 확인
            </button>
        </div>
        <button type="submit" id="findPw" disabled>비밀번호 변경</button>
    </form>



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
                   const findPwBtn = document.getElementById('findPw');
                   const sendBtn = document.getElementById('emailCode');
                   const checkValidate = document.getElementById('validate');
                   findPwBtn.disabled = false;
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
                   const findPwBtn = document.getElementById('findPw');
                   const emailInput = document.getElementById('email');
                   const validateBtn = document.getElementById('validate');

                   findPwBtn.disabled = false;
                   validateBtn.disabled = true;
                   emailInput.readOnly = true;
               })
               .catch(error => {
                   console.log('Error:', error);
                   alert(error.message);
               });
       }

    </script>

</body>
</html>
