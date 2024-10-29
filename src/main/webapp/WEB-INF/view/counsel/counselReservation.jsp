<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layout/header.jsp"%>


        변호사 정보
        <select id="lawyerInfo" name="lawyerInfo" onchange="showLawyerInfo()">
              <option value="" selected disabled>정보찾기</option>
            <c:forEach var="lawyer" items="${dtoList}">
                <option value="${lawyer.lawyerId}"
                        data-lawfirm="${lawyer.lawFirm}"
                        data-amount="${lawyer.counselingAmount}">
                            ${lawyer.lawyerName}
                </option>
            </c:forEach>
        </select>

    <!-- 변호사 정보를 표시할 영역 -->
    <div id="lawyerInfo">
        <p id="lawyerName"></p>
        <p id="lawyerFirm"></p>
        <p id="counselingAmount"></p>
    </div>

    <div>
        <table>
            <tr>
                <th>변호사명</th>
                <th>상담 시작 시간</th>
                <th>상담 종료 시간</th>
                <th>내용</th>
            </tr>
            <tr>
              <td>
                 <!-- 변호사 선택 -->
                 <select name="lawyerId" id="lawyerSelect" onchange="filterAvailableTimes()">
                     <option value="" selected disabled required>변호사</option>
                     <c:forEach var="lawyer" items="${dtoList}">
                         <option value="${lawyer.lawyerId}"
                            data-startTime="${lawyer.startTime}"
                            data-endTime="${lawyer.endTime}">
                          ${lawyer.lawyerName}</option>
                     </c:forEach>
                 </select>
             </td>

            <td><input type="datetime-local" id="startTime" required onchange="setEndTimeMin()"></td>
            <td><input type="datetime-local" id="endTime" required></td>
            <td><textarea id="content" rows="4" cols="50" placeholder="상담 내용을 입력하세요" required></textarea></td>
            </tr>
        </table>
        <button type="button" onclick="submitForm()">예약 제출</button>
    </div>

<script>

function setEndTimeMin() {
    const startTime = document.getElementById("startTime").value;
    const endTime = document.getElementById("endTime");

    if (startTime) {
        endTime.min = startTime;  // 상담 시작 시간 이후로만 상담 종료 시간을 선택 가능
    }
}

function filterAvailableTimes() {
    const select = document.getElementById("lawyerSelect");
    const selectedOption = select.options[select.selectedIndex];

    // 예약된 시작 시간과 종료 시간 가져오기
    let reservedStartTime = selectedOption.getAttribute("data-starttime") ? new Date(selectedOption.getAttribute("data-starttime").replace(" ", "T")) : null;
    let reservedEndTime = selectedOption.getAttribute("data-endtime") ? new Date(selectedOption.getAttribute("data-endtime").replace(" ", "T")) : null;

    console.log("예약 시작 시간:", reservedStartTime);
    console.log("예약 종료 시간:", reservedEndTime);

    // 예약 가능한 시간대: 09:00 ~ 18:00
    const officeStart = new Date();
    officeStart.setHours(9, 0, 0, 0);
    const officeEnd = new Date();
    officeEnd.setHours(18, 0, 0, 0);

    const startInput = document.getElementById("startTime");
    const endInput = document.getElementById("endTime");

    console.log("시작 시간 입력:", startInput);
    console.log("종료 시간 입력:", endInput);

    // 오늘 날짜 이후부터만 선택 가능하게 설정
    const minDate = new Date();
    minDate.setDate(minDate.getDate() + 1); // 오늘 이후로 설정

    // 예약된 시간이 없으면 기본 시간 설정
    startInput.min = minDate.toISOString().slice(0, 16); // 오늘 이후
    startInput.max = reservedStartTime ? reservedStartTime.toISOString().slice(0, 16) : officeEnd.toISOString().slice(0, 16);

    endInput.min = reservedEndTime ? reservedEndTime.toISOString().slice(0, 16) : officeStart.toISOString().slice(0, 16);
    endInput.max = officeEnd.toISOString().slice(0, 16);

    // 예약된 시간 제외 (예약된 시간과 겹치면 선택 불가)
    if (reservedStartTime && reservedEndTime) {
        startInput.addEventListener("change", function () {
            const selectedStartTime = new Date(startInput.value);

            // 선택한 시간이 예약된 시간과 겹치는지 확인
            if (selectedStartTime >= reservedStartTime && selectedStartTime <= reservedEndTime) {
                alert("이 시간은 이미 예약되었습니다. 다른 시간을 선택해주세요.");
                startInput.value = ""; // 입력한 값 초기화
            }
        });
    }
}

function showLawyerInfo() {
    const select = document.getElementById("lawyerInfo");
    const selectedOption = select.options[select.selectedIndex];

    const lawyerName = selectedOption.text;
    const lawFirm = selectedOption.getAttribute("data-lawfirm");
    const counselingAmount = selectedOption.getAttribute("data-amount");

    document.getElementById("lawyerName").innerText = lawyerName + "님의 정보";
    document.getElementById("lawyerFirm").innerText = "소속: " + lawFirm;
    document.getElementById("counselingAmount").innerText = "상담료: " + counselingAmount;
}

function submitForm(){
        const lawyerId = document.getElementById('lawyerSelect').value;
        const startTime = document.getElementById('startTime').value;
        const endTime = document.getElementById('endTime').value;
        const content = document.getElementById('content').value;

            const data = {
                lawyerId: lawyerId,
                startTime: startTime,
                endTime: endTime,
                content: content
            };
        fetch("/counsel/reservation",
           {method : 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
            })

        .then(response => response.json())
        .then(result => {
            if(result){
            console.log('Success:', result);
            alert("예약 신청 처리 되었습니다")
            window.location.href="/user/myPage"
            // 결과에 대한 처리
            }else{
            console.log('false:', result);
            alert("예약에 실패했습니다. 다시 시도해주세요")
            window.location.reload();
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("오류가 발생했습니다 다시 시도해주세요")
            window.location.reload();
        });
}
</script>

<%@ include file="../layout/footer.jsp" %>
