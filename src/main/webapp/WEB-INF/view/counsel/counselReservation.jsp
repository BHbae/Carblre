<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ include file="../layout/header.jsp"%>--%>


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