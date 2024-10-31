<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>
<style>
/* 버튼 공통 스타일 */
.btn {
	display: inline-block;
	background-color: #007bff;
	color: #ffffff;
	padding: 10px 15px;
	border-radius: 5px;
	text-decoration: none;
	transition: background-color 0.3s, transform 0.2s;
	border: none; /* 버튼의 기본 테두리 제거 */
	cursor: pointer; /* 마우스 포인터 변경 */
}

/* 작성 버튼 스타일 */
.btn-submit {
	background-color: #28a745; /* 초록색 */
}

.btn-submit:hover {
	background-color: #218838; /* 어두운 초록색 */
}

/* 목록으로 돌아가기 버튼 스타일 */
.btn-back {
	background-color: #6c757d; /* 회색 */
}

.btn-back:hover {
	background-color: #5a6268; /* 어두운 회색 */
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
	<section class="sub--section">
		<div class="inner--container">
			<h1 class="sub--title">공지사항 작성</h1>
		</div>
	</section>

	<div class="inner--container">
		<div class="sub--content">
			<form action="/notice/create" method="post">
				<div>
					<label for="title">제목:</label>
					<input type="text" id="title" name="title" required>
				</div>
				<div>
					<label for="content">내용:</label>
					<textarea id="content" name="content" required></textarea>
				</div>
				<div class="btn--wrap">
					<div id="list--button">
						<a href="/notice/notice">목록</a>
					</div>
					<div>
						<button type="submit" class="write--btn" style="border: none;">작성</button>
					</div>
				</div>
			</form>
		</div>
	</div>


	<%@ include file="../layout/footer.jsp"%>
</div>
