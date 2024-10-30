<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layout/header.jsp"%>


<section id="main--section" class="section">
        <p>${user.userName} 님의 예약 현황 </p>
    <c:choose>
        <c:when test="${empty counsel}">
                <p>현재 예약된 상담이 없습니다.</p>
        </c:when>

        <c:otherwise>

            <div class="list">
            <table border="1">
                <tr>
                    <th>신청자 이름</th>
                    <th>예약시간</th>
                    <th>내용</th>
                    <th>변호사</th>
                    <th>예약 현황</th>
                    <th>예약 수임</th>
                </tr>
                <tr>
                    <td>${counsel.userId}</td>
                    <td>${counsel.reservationTime}</td>
                    <td>${counsel.content}</td>
                    <td>${counsel.lawyerId}</td>
                    <td id="status">
                        <c:choose>
                            <c:when test="${counsel.status == 0}">
                                예약신청
                            </c:when>
                            <c:when test="${counsel.status == 1}">
                                수락
                            </c:when>
                            <c:when test="${counsel.status == 2}">
                                취소
                            </c:when>
                            <c:when test="${counsel.status == 3}">
                                종료
                            </c:when>
                        </c:choose>
                    </td>
                    <td>
                        <div class="change-status">
                            <select name="status" onchange="updateStatus()">
                                <option value="0" ${counsel.status == 0 ? 'selected' : ''}>신청 (0)</option>
                                <option value="1" ${counsel.status == 1 ? 'selected' : ''}>수락 (1)</option>
                                <option value="2" ${counsel.status == 2 ? 'selected' : ''}>취소 (2)</option>
                                <option value="3" ${counsel.status == 3 ? 'selected' : ''}>종료 (3)</option>
                            </select>
                            <button type="button" onclick="updateStatus()">변경</button>
                        </div>
                    </td>
                </tr>
            </table>
            </div>
        </c:otherwise>
    </c:choose>

</section>




<script>
function updateStatus() {
        const counselId = "${counsel.id}";// counsel.id로 사용
        const statusValue = document.querySelector('select[name="status"]').value;  // status 값을 const로 저장
      const requestData = {
          counselId: counselId,
          statusValue: statusValue,
      };
    fetch('/counsel/updateStatus', {
        method: 'POST',
        headers: {
              'Content-Type': 'application/json'
        },
          body: JSON.stringify(requestData)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
         // 상태 값에 따라 <td> 요소의 텍스트를 변경
                const statusElement = document.getElementById("status");

                switch (data.status) {
                    case 0:
                        statusElement.innerText = "예약신청";
                        break;
                    case 1:
                        statusElement.innerText = "수락";
                        break;
                    case 2:
                        statusElement.innerText = "취소";
                        break;
                    case 3:
                        statusElement.innerText = "종료";
                        break;
                    default:
                        statusElement.innerText = "알 수 없음";
                }

                alert("상태가 변경되었습니다.");
            } else {
                alert("상태 변경에 실패했습니다.");
            }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

</script>

<%@ include file="../layout/footer.jsp" %>
