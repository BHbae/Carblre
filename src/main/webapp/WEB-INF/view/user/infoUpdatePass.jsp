<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layout/header.jsp"%>

<p>비밀번호 변경</p>

<div class="form-group">
    <label>기존 비밀번호</label>
    <input type="password" class="form-control" id="originPass" placeholder="비밀번호 입력" oninput="debounceCheckPassword()" required>
    <p id="message"></p> <!-- 기존 비밀번호 확인 결과를 여기에 표시 -->
</div>

<form onsubmit="handleSubmit(event)">
    <!-- password 입력란 -->
    <div class="form-group">
        <label>변경할 비밀번호</label>
        <input type="password" class="form-control" id="changePassword" placeholder="비밀번호 입력" oninput="checkPasswordMatch()" required>
    </div>

    <!-- password 확인 입력란 -->
    <div class="form-group">
        <label>비밀번호 확인</label>
        <input type="password" class="form-control" id="checkPassword" placeholder="비밀번호 확인" oninput="checkPasswordMatch()" required>
    </div>

    <input type="hidden" id="id" name="id" value="${UserId}">
    <p id="passwordMessage"></p> <!-- 비밀번호 일치 여부를 여기에 표시 -->

    <button type="submit" disabled>비밀번호 변경</button>
</form>

<button onclick="history.back()">뒤로가기</button>

<script>
let debounceTimer;

function debounceCheckPassword() {
    clearTimeout(debounceTimer);  // 기존 타이머 취소
    debounceTimer = setTimeout(checkOriginPass, 1000);  // 1000ms 대기 후 실행
}

 // 기존 비밀번호 확인
  function checkOriginPass() {
      const originPass = document.getElementById('originPass').value;
        console.log("originpass",originPass);

      fetch('/user/checkOriginPass', {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json',
          },
          body: JSON.stringify({ originPass: originPass })
      })
      .then(response => {
          if (!response.ok) {
              throw new Error('서버 응답이 정상적이지 않습니다.');
          }
          return response.json();
      })
      .then(result => {
      console.log(result)
      console.log(originPass)
          const feedbackElement = document.getElementById('message');
          if (result.status === 1) {
              feedbackElement.textContent = '비밀번호가 일치합니다.';
              feedbackElement.style.color = 'green';
          } else {
              feedbackElement.textContent = '비밀번호가 일치하지 않습니다.';
              feedbackElement.style.color = 'red';
          }
      })
      .catch(error => {
          console.error('오류 발생:', error);
          const feedbackElement = document.getElementById('message');
          feedbackElement.textContent = '비밀번호 확인 중 오류가 발생했습니다.';
          feedbackElement.style.color = 'red';
      });
  }


  // 비밀번호 확인 함수
  function checkPasswordMatch() {
      const changedPassword = document.getElementById('changePassword').value;
      const checkedPassword = document.getElementById('checkPassword').value;
      const messageElement = document.getElementById('passwordMessage'); // 비밀번호 일치 여부 메시지
      const submitButton = document.querySelector('button[type="submit"]'); // 제출 버튼
      console.log(checkedPassword)
      console.log(changedPassword)
      console.log(messageElement)
      if (changedPassword !== checkedPassword) {
          messageElement.textContent = "비밀번호가 일치하지 않습니다.";
          messageElement.style.color = "red"; // 비밀번호가 일치하지 않을 경우 경고 메시지
          submitButton.disabled = true; // 비밀번호가 일치하지 않으면 버튼 비활성화
      } else {
          messageElement.textContent = "비밀번호가 일치합니다.";
          messageElement.style.color = "green"; // 비밀번호가 일치할 경우
          submitButton.disabled = false; // 비밀번호가 일치하면 버튼 활성화
      }
  }

  // 폼 제출 처리
  function handleSubmit(event) {
      event.preventDefault(); // 기본 폼 제출 방지

      const changedPassword = document.getElementById('changePassword').value;
      const checkedPassword = document.getElementById('checkPassword').value;
      const id = document.getElementById('id').value;

      const requestData = {
          changedPassword: changedPassword,
          checkedPassword: checkedPassword,
          id: id
      };

      fetch('/user/updatePass', {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          },
          body: JSON.stringify(requestData)
      })
      .then(response => {
          if (!response.ok) {
              throw new Error('Network response was not ok');
          }
          return response.json();
      })
      .then(data => {
          console.log(data.message);
          alert(data.message);
          window.location.href="/user/index";
      })
      .catch(error => {
          console.error("Error:", error);
          alert("오류가 발생했습니다: " + error.message);
      });
  }

</script>

<%@ include file="../layout/footer.jsp" %>
