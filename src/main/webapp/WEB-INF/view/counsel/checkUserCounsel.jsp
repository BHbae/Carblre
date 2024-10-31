<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layout/header.jsp"%>


    <section>
    <div class="list">
    <table border="1">
        <tr>
            <th>신청자 이름</th>
            <th>예약시작시간</th>
            <th>예약종료시간</th>
            <th>내용</th>
            <th>변호사</th>
            <th>예약 신청현황</th>
            <th>예약 수임</th>
            <th>예약 취소</th>
        </tr>
            <c:forEach var="counsel" items="${counselList}">
                <tr>
                    <td>${counsel.userId}</td>
                    <td>${counsel.startTime}</td>
                    <td>${counsel.endTime}</td>
                    <td>${counsel.content}</td>
                    <td>${counsel.lawyerId}</td>
                    <td id="status"> ${counsel.status}</td>
                    <td>
                        <input type="hidden" id="counselId-${counsel.id}" value="${counsel.id}">
                        <button type="button" onclick="confirmCancel(${counsel.id})">취소하기</button>
                    </td>
                </tr>
            </c:forEach>
    </table>
    </div>
    </section>

<script>

    function confirmCancel(counselId) {
        if (confirm("취소하시겠습니까?")) {
                            // '예'를 눌렀을 때만 업데이트 진행
            updateStatus(counselId);
        }
    }


  function updateStatus(counselId) {
              const setStatus="4" ;
              const setId = document.getElementById("counselId-" + counselId).value;
              const setData=
              { status:setStatus,
                id:setId }
                console.log("data",setData)
        fetch('/counsel/cancelStatus', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(setData)
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                // 상태를 업데이트: td 요소의 텍스트를 변경
                document.getElementById("status").innerText = data.newStatus;
                alert("상태가 '취소'로 변경되었습니다.");
                window.location.reload();
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
