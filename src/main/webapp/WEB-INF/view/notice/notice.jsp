<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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

			<div class="search-container" style="display: flex; justify-content: center; margin-bottom: 20px;">
				<form action="/notice/search" method="get" style="display: flex; align-items: center; width: 100%;">
					<input type="text" name="query" placeholder="검색어를 입력하세요" style="padding: 10px; border: 1px solid #ccc; border-radius: 4px; flex: 1; margin-right: 5px;"> <select name="type"
						style="padding: 10px; border: 1px solid #ccc; border-radius: 4px; margin-right: 5px;">
						<option value="title">제목</option>
						<option value="content">내용</option>
						<option value="all">제목+내용</option>
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
			<c:if test="${principal.role == 'admin'}">
				<div class="btn--wrap">
					<div id="write--button">
						<a href="/notice/create">글쓰기</a>
					</div>
				</div>
			</c:if>
			<ul class="page--button" style="display: flex; justify-content: center;">
				<c:if test="${currentPage > 1}">
					<li><a href="/notice/notice?page=${currentPage - 1}&size=${size}">◀</a></li>
				</c:if>

				<c:forEach var="i" begin="1" end="${totalPages}">
					<li class="${currentPage == i ? 'page--active' : ''}"><a href="/notice/notice?page=${i}&size=${size}" <c:if test="${currentPage == i}">style="pointer-events: none; color: white;"</c:if>> ${i} </a></li>
				</c:forEach>

				<c:if test="${currentPage < totalPages}">
					<li><a href="/notice/notice?page=${currentPage + 1}&size=${size}">▶</a></li>
				</c:if>

			</ul>
		</div>
	</div>

	<%@ include file="../layout/footer.jsp"%>
</div>
