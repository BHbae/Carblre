<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layout/header.jsp"%>

<section id="main--section" class="section">
    <p>${principal.userName} 님의 예약 현황 </p>

    <c:choose>
        <c:when test="${empty counselList}">
            <p>현재 예약된 상담이 없습니다.</p>
        </c:when>
        <c:otherwise>
            <div class="list">
                <table border="1">
                    <tr>
                        <th>신청자 이름</th>
                        <th>예약시작시간</th>
                        <th>예약종료시간</th>
                        <th>내용</th>
                        <th>변호사</th>
                        <th>예약 현황</th>
                        <th>예약 수임</th>
                    </tr>
                    <c:forEach var="counsel" items="${counselList}">
                        <tr>
                            <td>${counsel.userId}</td>
                            <td>${counsel.startTime}</td>
                            <td>${counsel.endTime}</td>
                            <td>${counsel.content}</td>
                            <td>${counsel.lawyerId}</td>
                            <td id="status-${counsel.id}">
                                <c:choose>
                                    <c:when test="${counsel.status == 0}">
                                        예약신청
                                    </c:when>
                                    <c:when test="${counsel.status == 1}">
                                        수락
                                    </c:when>
                                    <c:when test="${counsel.status == 2}">
                                        거절
                                    </c:when>
                                    <c:when test="${counsel.status == 3}">
                                        종료
                                    </c:when>
                                    <c:when test="${counsel.status == 4}">
                                        취소
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <div class="change-status">
                                    <select name="status" id="status-select-${counsel.id}" onchange="updateStatus(${counsel.id})">
                                        <option value="0" ${counsel.status == 0 ? 'selected' : ''}>신청 (0)</option>
                                        <option value="1" ${counsel.status == 1 ? 'selected' : ''}>수락 (1)</option>
                                        <option value="2" ${counsel.status == 2 ? 'selected' : ''}>거절 (2)</option>
                                        <option value="3" ${counsel.status == 3 ? 'selected' : ''}>종료 (3)</option>
                                        <option value="3" ${counsel.status == 4 ? 'selected' : ''}>취소 (4)</option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</section>

<script>
function updateStatus(counselId) {
    const statusSelectElement = document.getElementById("status-select-" + counselId);
    const statusValue = statusSelectElement.value;

    const requestData = {
        counselId: counselId,
            statusValue: statusValue
    };

    console.log("Request Data: ", requestData);

    fetch('http://192.168.0.36:8080/counsel/updateStatus', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("서버 응답 에러");
        }
        return response.json();
    })
    .then(data => {
        console.log("서버 응답: ", data);
        window.location.reload();
        // 처리 로직 추가
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

</script>

<%@ include file="../layout/footer.jsp"%>