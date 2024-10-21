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
            <button type="button" class="check--btn" id="checkValidate" onclick="checkValidate()"
                    disabled="disabled" style="cursor: pointer;">인증 확인
            </button>
        </div>
        <button type="submit">아이디 찾기</button>
    </form>

<div id="result"></div>


   <script>
      function sendValidate() {
           const email = document.getElementById('email').value;
           console.log('Email : ' + email);

           fetch('http://localhost:8080/send-mail/email?email=' + email)
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
                   // let username = document.getElementById('username').value;
                   // console.log(data.message.toString());
                   // username.innerText = data.message.toString();
                   const sendBtn = document.getElementById('emailCode');
                   const checkValidate = document.getElementById('checkValidate');
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

           fetch('http://localhost:8080/send-mail/checkValidate')
               .then(response => {
                   if (!response.ok) {
                       return response.json().then(data => {
                           throw new Error(data.message || '알 수 없는 에러가 발생했습니다.');
                       });
                   }
                   return response.json();  // 응답을 JSON 형식으로 변환
               })
               .then(data => {
                   // 서버로부터 받은 응답 데이터를 처리
                   console.log('Success:', data);
                   // 서버에서 보낸 메시지를 alert으로 표시
                   alert(data.message);
                   const signUpBtn = document.getElementById('signUp');
                   const emailInput = document.getElementById('userEmail');
                   const checkValidateBtn = document.getElementById('checkValidate');

                   signUpBtn.disabled = false;
                   checkValidateBtn.disabled = true;
                   emailInput.readOnly = true;

               })
               .catch(error => {
                   console.log('Error:', error);
                   // 에러 메시지를 alert으로 표시
                   alert(error.message);
               });
       }

       const autoHyphen = (target) => {
           target.value = target.value
               .replace(/[^0-9]/g, '')
               .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
       }

async function handleSubmit(event) {
    event.preventDefault();  // 기본 폼 제출 방지
    const email = document.getElementById('email').value;  // 폼에서 가져오는 이메일 값

    try {
        const response = await fetch('/user/email?email=' + email);

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const data = await response.json();
        console.log('Received data:', data);  // 데이터가 제대로 전달되는지 확인

        // 데이터가 null이거나 undefined일 경우 기본값 설정
        let userEmail = data.email || "이메일 없음";
        let userName = data.userName || "이름 없음";
        let userNickName = data.nickName || "닉네임 없음";


        // #result 요소가 있는지 확인 후 텍스트 삽입
        const resultElement = document.getElementById('result');
        if (!resultElement) {
            console.error('result 요소를 찾을 수 없습니다.');
            return;
        }
        if(userEmail==="이메일 없음"){
            alert("일치하는 아이디가 없습니다")
            location.reload();  // 페이지 새로고침
        } else {
        // HTML 업데이트
        resultElement.innerHTML = `
    <p>찾은 이메일: \${userEmail}</p>
    <p>사용자 이름: \${userName}</p>
    <p>닉네임: \${userNickName}</p>
        `;
        }
        console.log('HTML updated successfully');

    } catch (error) {
        console.error('Error:', error);
        alert("오류가 발생했습니다: " + error.message);
    }
}












    </script>
</body>
</html>
