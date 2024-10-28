<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<li><a href="/notice/notice">의뢰자</a></li>
					<li><a href="/aiounseling">AI 간편상담</a></li>
					<li><a href="/notice/notice">변호사</a></li>
					<li><a href="/notice/notice">공지사항</a></li>
					<li class="subtop--active"><a href="/cs/cs">고객센터</a></li>
				</ul>
			</div>
		</div>
	</section>
	<!-- 상단 이미지 & 서브 페이지 이동 END -->

	<div class="inner--container">
		<div class="sub--content">
			<h2 class="prih2">고객센터</h2>
			<div class="board--info">
				<div class="board--title">제목</div>
				<div class="board--detail">
					<span>작성자: ${dto.userName}</span> 
					<span>작성일: ${dto.requestTime}</span>
				</div>
			</div>
			<div class="board--content">
				<div>${dto.request}</div>
			</div>
			<div class="board--reply">
				<c:choose>
					<c:when test="${dto.response != null}">
						<div>${dto.response}</div>
						<div>${dto.responseTime}</div>
					</c:when>
					<c:otherwise>
						<div class="waitingbox">답변을 기다리는중입니다</div>
					</c:otherwise>
				</c:choose>


			</div>
			<div class="btn btn--wrap">
				<div id="list--button">
					<a href="/cs/cs">목록</a>
				</div>
				<div id="edit--button">
					<button onclick="location.href='/cs/edit/${dto.id}'">수정하기</button>
				</div>
			</div>
		</div>
	</div>

</div>

<%@ include file="../layout/footer.jsp"%>
