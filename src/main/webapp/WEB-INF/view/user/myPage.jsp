<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layout/header.jsp" %>

<div class="wrap">
    <!-- 상단 이미지 & 서브 페이지 이동 START -->
    <section class="sub--section">
        <div class="section--background" id="notice--background">
            <div class="inner--container">
                <sub class="eng">My Page</sub>
                <h1 class="sub--title">마이페이지</h1>
            </div>
        </div>
        <div style="border-bottom: 1px solid #bababa;">
            <div class="inner--container">
                <ul class="sub--top--menu">
                    <li class="subtop--active"><a href="/user/mypage">마이페이지</a></li>
                </ul>
            </div>
        </div>
    </section>
    <!-- 상단 이미지 & 서브 페이지 이동 END -->

    <div class="inner--container">
        <div class="sub--content">
            <h2 class="prih2 user--form">마이페이지</h2>

            <div class="info infoupdate">
                <div class="btn btn--wrap">
                    <div id="mypage--counsel">
                        <c:choose>
                            <%-- principal.role이 'user'일 경우 --%>
                            <c:when test="${principal.role == 'user'}">
                                <p class="update-info" onclick="checkUserCounsel()"> 예약 현황 </p>
                            </c:when>

                            <%-- principal.role이 'lawyer'일 경우 --%>
                            <c:when test="${principal.role == 'lawyer'}">
                                <p class="update-info" onclick="checkLawyerCounsel()"> 변호 예약 현황 </p>
                            </c:when>
                        </c:choose>
                    </div>
                </div>



                <c:if test="${principal.site != '서버'}">
                    <p class="label--info">이름</p>
                    <p class="label--value">${principal.userName}</p>
                    <p class="label--info">아이디</p>
                    <p class="label--value">${principal.site}</p>
                    <p class="label--info">이메일</p>
                    <p class="label--value">${principal.email}</p>
                </c:if>
                <c:if test="${principal.site == '서버'}">
<<<<<<< HEAD
                    <p class="label--info">이름</p>
                    <p class="label--value">${principal.userName}</p>
                    <p class="label--info">아이디</p>
                    <p class="label--value">${principal.nickName}</p>
                    <p class="label--info">이메일</p>
                    <p class="label--value">${principal.email}</p>

                    <c:if test="${principal.role == 'lawyer'}">
                        <p class="label--info">법인명</p>
                        <p class="label--value">${lawyer.lawFirm}</p>
                        <p class="label--info">법인 전화번호</p>
                        <p class="label--value">${lawyer.officeNum}</p>
                        <p class="label--info">소개</p>
                        <p class="label--value">${lawyer.introduction}</p>
                        <p class="label--info">프로필 사진</p>
                        <div class="label--value--image"><img src="${lawyer.uploadProfileName}" alt="Profile Image" /></div>
                        <p class="label--info">법인 사진</p>
                        <div class="label--value--image"><img src="${lawyer.uploadLicenseName}" alt="Co. Image" /></div>
                        <p class="label--info">지정 10분 상담가격</p>
                        <p class="label--value">${lawyer.counselingAmount}</p>
=======
                    <p> 이름: ${principal.userName}</p>
                    <p> 아이디: ${principal.nickName}</p>
                    <p> 이메일: ${principal.email}</p>
                    <c:if test="${principal.role == 'lawyer'}">
                        <p> 법인명: ${lawyer.lawFirm}</p>
                        <p> 법인 전화번호: ${lawyer.officeNum}</p>
                        <p> 소개: ${lawyer.introduction}</p>
                        <p> 프로필 사진: </p>
                        <img src="${lawyer.uploadProfileName}" alt="Profile Image" />
                        <p> 지정 10분 상담가격: ${lawyer.counselingAmount}</p>
>>>>>>> 85fbbfca8fa10b8f4ec427f75e731d5c65e50068
                    </c:if>
                </c:if>
                <div class="btn btn--wrap">
                    <div id="mypage--edit">
                        <p class="update-info" onclick="infoUpdate()"> 개인정보 수정 </p>
                    </div>
                    <div id="mypage--password">
                        <p class="update-info" onclick="infoUpdatePass()"> 비밀번호 변경 </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>

    function checkUserCounsel() {
        window.location.href = "/user/checkUserCounsel";
    }

    function checkLawyerCounsel() {
        window.location.href = "/user/checkLawyerCounsel";
    }


    function infoUpdate() {
        window.location.href = "/user/infoUpdate";
    }

    function infoUpdatePass() {
        window.location.href = "/user/infoUpdatePass";
    }
</script>

<%@ include file="../layout/footer.jsp" %>