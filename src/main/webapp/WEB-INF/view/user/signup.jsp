<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="wrap">
    <section style="margin-bottom: 100px"></section>
    <div class="inner--container">
        <div class="sub--content">
            <h2 class="prih2">회원가입</h2>


            <form action="/user/signUp" method="POST" class="infoupdate">
                <!-- 사용자 이름 -->
                <div class="form-group">
                    <label for="userName">이름 </label>
                    <input type="text" class="form-control" id="userName" placeholder="이름을 입력하세요." name="userName"
                           required>
                </div>

                <div class="form-group">
                    <label for="nickName">아이디</label>
                    <input type="text" class="form-control" id="nickName" placeholder="아이디를 입력하세요." name="nickName"
                           required>
                    <button type="button" class="check--btn" id="checkId" onclick="checkDuplicate()">
                        중복 확인
                    </button>
                </div>

                <div class="form-group">
                    <label for="password">비밀번호</label>
                    <input type="password" class="form-control" id="password" placeholder="비밀번호를 입력하세요." name="password"
                           oninput="pwCheck()" minlength="8" maxlength="20" required>
                    <p class="pw--info">
                        8자 이상 20자 이하 입력 (공백 제외) <br>영문/숫자/특수문자(!@#$%^&*)포함
                    </p>
                </div>

                <div class="form-group">
                    <label for="passwordCheck">비밀번호 확인
                        <span id="pwConfirm"></span>
                    </label>
                    <input type="password" class="form-control" id="passwordCheck" placeholder="비밀번호를 한 번 더 입력하세요."
                           name="passwordCheck" oninput="pwCheck()" minlength="8" maxlength="20" required>
                </div>

                <!-- 이메일 입력란 -->
                <div class="form-group">
                    <label for="email">이메일</label>
                    <input type="email" class="form-control" id="email" placeholder="example@carblre.com" name="email"
                           value="" required>
                <!-- 이메일 인증코드 발송 -->
                    <button type="button" class="check--btn" id="emailCode" onclick="sendValidate()">발송</button>
                    <button type="button" class="check--btn" id="checkValidate" onclick="checkValidate()"
                            disabled="disabled" style="cursor: pointer;">인증 확인
                    </button>
                </div>

                <!-- 제출 버튼 -->
                <button type="submit" id="signUp">가입하기</button>
            </form>
        </div>
    </div>
</div>


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


    // '중복 확인' 버튼을 클릭하면 실행됩니다.
    function checkDuplicate() {
        const userId = document.getElementById('nickName').value;
        console.log('userId : ', userId);

        fetch(`http://localhost:8080/user/checkId?userId=` + userId)
            .then(response => {
                if (!response.ok) {
                    return response.json().then(data => {
                        throw new Error(data.message || '알 수 없는 에러가 발생했습니다.');
                    });
                }
                return response.json();  // 응답을 JSON 형식으로 변환
            })
            .then(data => {
                if (data.message) {
                    alert("사용 가능한 아이디입니다.");
                    const result = confirm("해당 아이디를 사용하시겠습니까?");

                    if (result) {
                        const inputUserId = document.getElementById('nickName').value;
                        inputUserId.disabled = true;
                    }

                } else {
                    alert("사용 중인 아이디입니다.");
                }
            })
            .catch(error => {
                console.log('Error:', error);
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
                const emailInput = document.getElementById('email');
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

</script>
<%@ include file="../layout/footer.jsp" %>