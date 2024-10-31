<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../layout/header.jsp"%>
<div class="wrap">
	<!-- 상단 이미지 & 서브 페이지 이동 START -->
	<section class="sub--section">
		<div class="section--background" id="notice--background">
			<div class="inner--container">
				<sub class="eng">Notice</sub>
				<h1 class="sub--title">공지사항</h1>
			</div>
		</div>
		<div style="border-bottom: 1px solid #bababa;">
			<div class="inner--container">
				<ul class="sub--top--menu">
					<li><a href="/notice/notice">의뢰자</a></li>
					<li><a href="/aiounseling">AI 간편상담</a></li>
					<li><a href="/notice/notice">변호사</a></li>
					<li class="subtop--active"><a href="/notice/notice">공지사항</a></li>
					<li><a href="/cs/cs">고객센터</a></li>
				</ul>
			</div>
		</div>
	</section>
	<!-- 상단 이미지 & 서브 페이지 이동 END -->

	<div class="inner--container">
		<div class="sub--content">
			<h2 class="prih2">${notice.title}</h2>
			<div class="notice-details">
				<p>
					<strong>작성일:</strong>
					<fmt:formatDate value="${notice.createdAt}" pattern="yyyy-MM-dd HH:mm" />
				</p>
				<p>
					<strong>내용:</strong>
				</p>
				<div>${notice.content}</div>
			</div>
			<a href="/notice/notice" class="btn back-button">목록으로 돌아가기</a>

			<c:if test="${principal.role == 'admin'}">
				<a href="/notice/update/${notice.id}" class="btn btn-edit">수정</a>
				<form action="/notice/delete/${notice.id}" method="post" style="display: inline;">
					<button type="submit" class="btn btn-danger" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</button>
				</form>
			</c:if>
		</div>
	</div>


	<%@ include file="../layout/footer.jsp"%>
</div>
