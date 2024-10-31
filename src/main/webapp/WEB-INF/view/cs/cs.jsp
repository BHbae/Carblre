<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>

<div class="wrap">
	<!-- 상단 이미지 & 서브 페이지 이동 START -->
	<section class="sub--section">
		<div class="section--background" id="notice--background">
			<div class="inner--container">
				<sub class="eng">Customer Service Center</sub>
				<h1 class="sub--title">고객센터</h1>
			</div>
		</div>
		<div style="border-bottom: 1px solid #bababa;">
			<div class="inner--container">
				<ul class="sub--top--menu">
					<li><a href="/board/boardList">의뢰자</a></li>
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

			<div class="search-container" style="display: flex; justify-content: center; margin-bottom: 20px;">
				<form action="/cs/search" method="get" style="display: flex; align-items: center; width: 100%;">
					<input type="text" name="query" placeholder="검색어를 입력하세요" value="${query}" style="padding: 10px; border: 1px solid #ccc; border-radius: 4px; flex: 1; margin-right: 5px;"> <select name="type"
						style="padding: 10px; border: 1px solid #ccc; border-radius: 4px; margin-right: 5px;">
						<option value="title" <c:if test="${type == 'title'}">selected</c:if>>제목</option>
						<option value="content" <c:if test="${type == 'content'}">selected</c:if>>내용</option>
						<option value="all" <c:if test="${type == 'all'}">selected</c:if>>제목+내용</option>
					</select>
					<button type="submit" style="padding: 10px 15px; background-color: #007BFF; color: white; border: none; border-radius: 4px; cursor: pointer; transition: background-color 0.3s;">
						<strong>검색</strong>
					</button>
				</form>
			</div>

			<table class="table">
				<thead>
					<th>No</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</thead>
				<tbody>
					<c:forEach items="${csList}" var="csList">
						<tr>
							<td class="notice--no">${csList.id}</td>
							<td class="notice--con"><a href="/cs/detail/${csList.id}">${csList.title}</a></td>
							<td class="notice--name">${csList.userName}</td>
							<td class="notice--date">${csList.requestTime}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="btn--wrap">
				<div id="write--button">
					<a href="/cs/write">글쓰기</a>
				</div>
			</div>
			<ul class="page--button" style="display: flex; justify-content: center;">
				<c:if test="${currentPage > 1}">
					<li><a href="/cs/cs?page=${currentPage - 1}&size=${size}">◀</a></li>
				</c:if>

				<c:forEach var="i" begin="1" end="${totalPages}">
					<li class="${currentPage == i ? 'page--active' : ''}"><a href="/cs/cs?page=${i}&size=${size}" <c:if test="${currentPage == i}">style="pointer-events: none; color: white;"</c:if>> ${i} </a></li>
				</c:forEach>

				<c:if test="${currentPage < totalPages}">
					<li><a href="/cs/cs?page=${currentPage + 1}&size=${size}">▶</a></li>
				</c:if>
			</ul>
		</div>
	</div>

	<%@ include file="../layout/footer.jsp"%>