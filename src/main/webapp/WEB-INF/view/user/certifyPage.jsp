<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>
<style>
    #hiddenElement {
        display : none;
    }
</style>
<div class="container">
    <h1>휴대전화 인증</h1>

        <!-- 사용자 전화번호 -->
        <div class="form-group">
            <label for="userPhoneNumber">전화번호</label>
            <input type="text" class="form-control" id="userPhoneNumber" placeholder="전화번호를 입력하세요." name="userPhoneNumber" required>
        </div>

        <div class="form-group">
            <label for="sendButton">
                <button type="button" class="form-control" id="sendButton" onclick="sendCertificationCode()">발송</button>
            </label>
        </div>

        <div id="hiddenElement">
            <label for="inputCertify">
                <input type="text" class="form-control" id="inputCertify" placeholder="인증번호를 입력하세요.">
            </label>
            <label for="certifyButton">
                <button type="button" class="form-control" id="certifyButton" onclick="checkCertificationCode()">인증</button>
            </label>
        </div>



    <script>

    function sendCertificationCode()
    {
        const userInputPhoneNumber = document.getElementById('userPhoneNumber').value;

        const data =
            {
                phoneNumber : userInputPhoneNumber
            }

        fetch("http://192.168.0.36:8080/sms/send" , {
            method : 'POST',
            headers :
                {
                    'Content-Type' : 'application/json'
                },
            body : JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => {

                console.log('Success', data);
                alert(data.message);

                const sendButton = document.getElementById('sendButton');
                sendButton.disabled = true;
                const element = document.getElementById('hiddenElement');
                element.style.display = 'block';
            })
            .catch(error => {

                console.log('Error', error);
                alert(error.message);

            });
    }


    function checkCertificationCode()
    {
        const userInputCode = document.getElementById('inputCertify').value;

        const data =
            {
                userInputCode : userInputCode
            }

        fetch("http://192.168.0.36:8080/sms/certify", {
            method : 'POST',
            headers :
                {
                    'Content-Type' : 'application/json'
                },
            body : JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => {
                if(data.successMessage === "true")
                {
                    console.log('Success', data);
                    alert("인증이 완료되었습니다.");
                    window.location.href = "/user/signIn";  // TODO 링크 수정
                }
                else if (data.successMessage === "false")
                {
                    console.log('Fail', data);
                    alert("인증번호가 올바르지 않습니다. 다시 시도하세요.");
                }

            })
            .catch(error => {

                console.log('Error', error);
                alert(error.message);

            });
    }


    </script>
<%@ include file="../layout/footer.jsp" %>