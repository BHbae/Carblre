<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					<li><a href="/lawyer/lawyers">변호사</a></li>
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
			<div class="btn btn--wrap">
				<div id="write--button">
					<a href="/cs/write">글쓰기</a>
				</div>
			</div>
			<ul class="page--button btn">
				<li
					class="page-item <c:if test='${currentPage == 1}'>disabled</c:if>">
					<a class="page-link" href="/cs/cs?page=${currentPage - 1}">◀</a>
				</li>
				<c:forEach begin="1" end="${totalPages}" var="page">
                    <li class="<c:if test="${currentPage == page}">page--active</c:if>" ><a href="/cs/cs?page=${page}">${page}</a></li>
                </c:forEach>
				<li
					class="page-item <c:if test='${currentPage == totalPages}'>disabled</c:if>">
					<a class="page-link" href="/cs/cs?page=${currentPage + 1}">▶</a>
				</li>
			</ul>
		</div>
	</div>

	<%@ include file="../layout/footer.jsp"%>