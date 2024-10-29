<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layout/header.jsp"%>

<div class="wrap">
    <!-- 상단 이미지 & 서브 페이지 이동 START -->
    <section class="sub--section">
        <div class="section--background" id="notice--background">
            <div class="inner--container">
                <sub class="eng">Notice</sub>
                <h1 class="sub--title">고객센터</h1>
            </div>
        </div>
        <div style="border-bottom: 1px solid #bababa;">
            <div class="inner--container">
                <ul class="sub--top--menu">
                    <li class="subtop--active"><a href="/user/infoUpdate">개인정보수정</a></li>
                </ul>
            </div>
        </div>
    </section>
    <!-- 상단 이미지 & 서브 페이지 이동 END -->

    <div class="inner--container">
        <div class="sub--content">
            <h2 class="prih2">개인정보수정</h2>

            <form action="/user/infoUpdate" method="POST" class="infoupdate">
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
                <div class="form-group emailsend">
                    <label for="email">이메일</label>
                    <input type="email" class="form-control" id="email" placeholder="example@carblre.com" name="email"  required>
                </div>

                <!-- 이메일 인증코드 발송 -->
                <div class="form-group">
                    <button type="button" class="check--btn" id="emailCode" onclick="sendValidate()">발송</button>
                    <button type="button" class="check--btn" id="checkValidate" onclick="checkValidate()" disabled="disabled" style="cursor: pointer;">인증 확인
                    </button>
                </div>

                <!-- 제출 버튼 -->
                <div class="btn btn--wrap">
                    <div id="edit--button">
                        <button type="submit">수정하기</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="../layout/footer.jsp" %>
