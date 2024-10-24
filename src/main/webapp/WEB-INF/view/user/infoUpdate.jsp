<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layout/header.jsp"%>






 <form action="/user/infoUpdate" method="POST">
        <!-- 사용자 이름 -->
        <div class="form-group">
            <label for="userName">이름 </label>
            <input type="text" class="form-control" id="userName" placeholder="이름을 입력하세요." value="${originUser.userName}" name="userName" required readonly >
        </div>

        <div class="form-group">
            <label for="nickName">아이디</label>
            <input type="text" class="form-control" id="nickName" placeholder="아이디를 입력하세요." value="${originUser.nickName}" name="nickName" required readonly >
        </div>


        <!-- 이메일 입력란 -->
        <div class="form-group">
            <label for="email">이메일</label>
            <input type="email" class="form-control" id="email" placeholder="example@carblre.com" name="email"  required>
        </div>

        <!-- 이메일 인증코드 발송 -->
        <div class="form-group">
            <button type="button" class="check--btn" id="emailCode" onclick="sendValidate()">발송</button>
            <button type="button" class="check--btn" id="checkValidate" onclick="checkValidate()"
                    disabled="disabled" style="cursor: pointer;">인증 확인
            </button>
        </div>

        <!-- 제출 버튼 -->
        <button type="submit"  id="updateInfo">수정하기</button>
    </form>
<script>

</script>

<%@ include file="../layout/footer.jsp" %>
