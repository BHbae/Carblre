<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container">
    <h1>회원가입</h1>

    <form action="/user/signUp" method="POST">
        <!-- 사용자 이름 -->
        <div class="form-group">
            <label for="userName">이름 </label>
            <input type="text" class="form-control" id="userName" placeholder="이름을 입력하세요." name="userName" required>
        </div>

        <div class="form-group">
            <label for="nickName">아이디</label>
            <input type="text" class="form-control" id="nickName" placeholder="아이디를 입력하세요." name="nickName" required>
            <button type="button" class="check--btn" id="checkId" onclick="checkDuplicate()">
                중복 확인
            </button>
        </div>

        <div class="form-group">
            <label for="password">비밀번호</label>
            <input type="password" class="form-control" id="password" placeholder="비밀번호를 입력하세요." name="password"  minlength="8" maxlength="20" required>
            <p class="pw--info">
                8자 이상 20자 이하 입력 (공백 제외) <br>영문/숫자/특수문자(!@#$%^&*)포함
            </p>
        </div>

        <div class="form-group">
            <label for="passwordCheck">비밀번호 확인
                <span id="pwConfirm">

                </span>
            </label>
            <input type="password" class="form-control" id="passwordCheck" placeholder="비밀번호를 한 번 더 입력하세요." name="passwordCheck" minlength="8" maxlength="20" required>
        </div>

        <!-- 이메일 입력란 -->
        <div class="form-group">
            <label for="email">이메일</label>
            <input type="email" class="form-control" id="email" placeholder="example@carblre.com" name="email" value="" required>
        </div>

        <!-- 이메일 인증코드 발송 -->
        <div class="form-group">
            <button type="button" class="check--btn" id="emailCode" onclick="sendValidate()">발송</button>
            <button type="button" class="check--btn" id="checkValidateBtn" onclick="checkValidate()" disabled="disabled" style="cursor: pointer;">인증 확인</button>
        </div>

        <!-- 제출 버튼 -->
        <button type="submit" id="signUp" disabled="disabled">가입하기</button>
    </form>
</div>
<script>

    // '발송' 버튼을 클릭하면 실행됩니다.
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
                const sendBtn = document.getElementById('emailCode');
                const checkValidateBtn = document.getElementById('checkValidateBtn');
                sendBtn.disabled = true;
                checkValidateBtn.disabled = false;
            })
            .catch(error => {
                console.log('Error:', error);
                // 에러 메시지를 alert으로 표시
                alert(error.message);
            });
    }

<%@ include file="../layout/footer.jsp"%>
