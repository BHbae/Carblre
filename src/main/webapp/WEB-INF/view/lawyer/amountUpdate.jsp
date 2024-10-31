<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상담가 수정</title>
</head>
<body>

    <form onsubmit="handleSubmit(event)">
        <div class="form-group">
            <p>현재 상담가 : ${dto.counselingAmount}</p>
            <label for="counselingAmount">수정 상담 가격</label>
            <input type="text" class="form-control" id="counselingAmount" placeholder="가격 입력" name="counselingAmount" value="" required>
        </div>

        <button type="submit">수정하기</button>
    </form>

   <script>
function handleSubmit(event) {
    event.preventDefault();
    const counselingAmount = document.getElementById('counselingAmount').value;
    console.log(counselingAmount);
    const requestData = { counselingAmount: counselingAmount };

    fetch('/lawyer/amountUpdate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestData)
    })
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

        if (data.status === 1) {
            alert(data.message);
            window.opener.location.reload();
            window.close();  // 창을 닫음
        } else {
            alert(data.message);
            window.close();  // 창을 닫음
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
