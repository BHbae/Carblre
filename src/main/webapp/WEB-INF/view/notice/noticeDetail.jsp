<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../layout/header.jsp"%>
<style>
/* 상세보기 컨테이너 스타일 */
.inner--container {
	margin: 20px auto;
	padding: 20px;
	background-color: #fdfdfd;
	border-radius: 5px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

/* 제목 스타일 */
.prih2 {
	font-size: 28px;
	color: #333;
	margin-bottom: 15px;
}

/* 공지사항 상세 내용 스타일 */
.notice-details {
	background-color: #ffffff;
	border: 1px solid #e0e0e0;
	border-radius: 5px;
	padding: 15px;
	margin-bottom: 20px;
}

.notice-details p {
	font-size: 16px;
	color: #555;
	line-height: 1.6;
}

.notice-details strong {
	color: #333;
}

/* 내용 블록 스타일 */
.notice-details div {
	font-size: 14px;
	color: #444;
	margin-top: 10px;
}

/* 돌아가기 버튼 스타일 */
.btn {
	display: inline-block;
	background-color: #007bff;
	color: #ffffff;
	padding: 10px 15px;
	border-radius: 5px;
	text-decoration: none;
	transition: background-color 0.3s;
}

.btn:hover {
	background-color: #0056b3;
}

/* 반응형 디자인 */
@media ( max-width : 600px) {
	.inner--container {
		padding: 15px;
	}
	.prih2 {
		font-size: 24px;
	}
	.notice-details {
		padding: 10px;
	}
	.btn {
		padding: 8px 12px;
	}
}
</style>
<div class="wrap">
	<!-- 상단 이미지 & 서브 페이지 이동 START -->
	<section class="sub--section">
		<div style="border-bottom: 1px solid #bababa;">
			<div class="inner--container">
				<ul class="sub--top--menu">
					<li><a href="/notice/notice">의뢰자</a></li>
					<li><a href="/aiounseling">AI 간편상담</a></li>
					<li><a href="/reservation/reservation">변호사</a></li>
					<li><a href="/notice/notice">공지사항</a></li>
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
					<strong>작성일:</strong> <fmt:formatDate value="${notice.createdAt}" pattern="yyyy-MM-dd HH:mm" />
				</p>
				<p>
					<strong>내용:</strong>
				</p>
				<div>${notice.content}</div>
			</div>
			<a href="/notice/notice" class="btn back-button">목록으로 돌아가기</a>
		</div>
	</div>

	<%@ include file="../layout/footer.jsp"%>
</div>
