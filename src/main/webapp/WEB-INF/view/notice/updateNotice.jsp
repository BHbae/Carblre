<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>
<div class="wrap">
	<section class="sub--section">
		<div class="inner--container">
			<h1 class="sub--title">공지사항 수정</h1>
		</div>
	</section>

	<div class="inner--container">
		<div class="sub--content">
			<form action="/notice/update/${notice.id}" method="post">
				<div>
					<label for="title">제목:</label> <input type="text" id="title" name="title" value="${notice.title}" required>
				</div>
				<div>
					<label for="content">내용:</label>
					<textarea id="content" name="content" required>${notice.content}</textarea>
				</div>
				<div>
					<button type="submit">수정</button>
				</div>
			</form>
			<a href="/notice/notice">목록으로 돌아가기</a>
		</div>
	</div>

	<%@ include file="../layout/footer.jsp"%>
</div>
