<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layout/header.jsp"%>

<p class="update-info"  onclick="infoUpdate()"> 개인정보 수정 </p>
<p class="update-info"onclick="infoUpdatePass()" > 비밀번호 변경 </p>

<script>
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
