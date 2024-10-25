<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layout/header.jsp"%>
<style>
.update-info {
    display: inline-block;            /* 버튼처럼 보이도록 */
    padding: 10px 20px;               /* 패딩을 주어 크기 조절 */
    background-color: #4CAF50;        /* 버튼 배경색 (초록색) */
    color: white;                     /* 글자 색상 (흰색) */
    border: none;                     /* 테두리 제거 */
    border-radius: 5px;               /* 테두리 둥글게 */
    text-align: center;               /* 텍스트 가운데 정렬 */
    cursor: pointer;                  /* 마우스 커서를 포인터로 변경 */
    font-size: 16px;                  /* 글자 크기 */
    text-decoration: none;            /* 밑줄 제거 */
}

.update-info:hover {
    background-color: #45a049;        /* 호버 시 배경색 변경 */
}

</style>
<h2> 유저 정보</h2>
<c:if test="${principal.site != '서버'}">
<p>  이름: ${principal.userName}</p>
<p>  아이디: ${principal.site}</p>
<p>  이메일: ${principal.email}</p>
</c:if>
<div class="info">
<p>  이름: ${principal.userName}</p>
<p>  아이디: ${principal.nickName}</p>
<p>  이메일: ${principal.email}</p>

</div>

<c:choose>
    <%-- principal.role이 'user'일 경우 --%>
    <c:when test="${principal.role == 'user'}">
<p class="update-info"  onclick="checkUserCounsel()"> 예약 현황 </p>
    </c:when>

    <%-- principal.role이 'lawyer'일 경우 --%>
    <c:when test="${principal.role == 'lawyer'}">
<p class="update-info"  onclick="checkLawyerCounsel()"> 변호 예약 현황 </p>
    </c:when>
</c:choose>
<p class="update-info"  onclick="infoUpdate()"> 개인정보 수정 </p>
<p class="update-info"onclick="infoUpdatePass()" > 비밀번호 변경 </p>




<script>

function checkUserCounsel(){
    window.location.href = "/user/checkUserCounsel";
}
function checkLawyerCounsel(){
    window.location.href = "/user/checkLawyerCounsel";
}


function infoUpdate()
{
    window.location.href = "/user/infoUpdate";
}
function infoUpdatePass()
{
    window.location.href = "/user/infoUpdatePass";
}
</script>

<%@ include file="../layout/footer.jsp" %>
