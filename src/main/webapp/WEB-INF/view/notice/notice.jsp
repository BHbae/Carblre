<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
					<li><a href="/reservation/reservation">변호사</a></li>
					<li class="subtop--active"><a href="/notice/notice">공지사항</a></li>
					<li><a href="/cs/cs">고객센터</a></li>
				</ul>
			</div>
		</div>
	</section>
	<!-- 상단 이미지 & 서브 페이지 이동 END -->

	<div class="inner--container">
		<div class="sub--content">
			<h2 class="prih2">공지사항</h2>

			<table class="table">
				<thead>
					<th>No</th>
					<th>제목</th>
					<th>내용</th>
					<th>작성일</th>
				</thead>
				<tbody>
					<c:forEach var="notice" items="${noticeList}">
						<tr onclick="location.href='/notice/${notice.id}'" style="cursor: pointer;">
							<td>${notice.id}</td>
							<td><c:choose>
									<c:when test="${fn:length(notice.title) > 20}">
										${fn:substring(notice.title, 0, 20)}...
									</c:when>
									<c:otherwise>
										${notice.title}
									</c:otherwise>
								</c:choose></td>
							<td><c:choose>
									<c:when test="${fn:length(notice.content) > 50}">
										${fn:substring(notice.content, 0, 50)}...
									</c:when>
									<c:otherwise>
										${notice.content}
									</c:otherwise>
								</c:choose></td>
							<td><fmt:formatDate value="${notice.createdAt}" pattern="yyyy-MM-dd HH:mm" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<ul class="page--button btn">
				<li>◀</li>
				<li class="page--active">1</li>
				<li>2</li>
				<li>3</li>
				<li>▶</li>
			</ul>
		</div>
	</div>

	<%@ include file="../layout/footer.jsp"%>