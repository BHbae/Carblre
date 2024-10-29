<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="wrap">
    <!-- 상단 이미지 & 서브 페이지 이동 START -->
    <section class="sub--section">
        <div class="section--background" id="notice--background">
            <div class="inner--container">
                <sub class="eng">Client</sub>
                <h1 class="sub--title">의뢰자</h1>
            </div>
        </div>
        <div style="border-bottom: 1px solid #bababa;">
            <div class="inner--container">
                <ul class="sub--top--menu">
                    <li class="subtop--active"><a href="/board/boardList">의뢰자</a></li>
                    <li><a href="/aiounseling">AI 간편상담</a></li>
                    <li><a href="/lawyer/lawyers">변호사</a></li>
                    <li><a href="/notice/notice">공지사항</a></li>
                    <li><a href="/cs/cs">고객센터</a></li>
                </ul>
            </div>
        </div>
    </section>
    <!-- 상단 이미지 & 서브 페이지 이동 END -->

<<<<<<< HEAD
    <div class="inner--container">
        <div class="sub--content">
            <h2 class="prih2">의뢰자</h2>
            <table class="table">
                <thead>
                <th>No</th>
                <th>제목</th>
                <th>작성자 ()추후 아이디로 변경)</th>
                <th>작성일</th>
                </thead>
                <tbody>
                <c:forEach items="${boards}" var="boards">
                    <tr>
                        <td class="notice--no">${boards.id}</td>
                        <td class="notice--con"><a href="<c:url value='/board/detail/${boards.id}' />">${boards.title}</a></td>
                        <td class="notice--name">${boards.userId}</td>
                        <td class="notice--date">${boards.createdAt}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="btn btn--wrap">
                <div id="write--button">
                    <a href="/board/createBoard">글쓰기</a>
                </div>
            </div>
            <ul class="page--button btn">
                <li
                        class="page-item <c:if test='${currentPage == 1}'>disabled</c:if>">
                    <a class="page-link" href="/cs/cs?page=${currentPage - 1}">◀</a>
                </li>
                <c:forEach begin="1" end="${totalPages}" var="page">
                    <li class="<c:if test="${currentPage == page}">page--active</c:if>"><a
                            href="/cs/cs?page=${page}">${page}</a></li>
                </c:forEach>
<<<<<<< HEAD
                <li class="page-item <c:if test='${currentPage == totalPages}'>disabled</c:if>">
                    <a class="page-link" href="/cs/cs?page=${currentPage + 1}">▶</a>
                </li>
            </ul>
=======
=======
<main>
		<table>
			<tr>
				<th>게시글 번호</th>
				<th>제목</th>
				<th>작성자 ()추후 아이디로 변경)</th>
			</tr>
	<c:forEach items="${boards}" var="boards">
			<tr>
				<td>${boards.id}</td>
				<td>
    				<a href="<c:url value='/detail/${boards.id}' />">${boards.title}</a>
				</td>
				<td>${boards.createdAt}</td>
			</tr>
	</c:forEach>
		</table>
</main>
>>>>>>> 39cb0b06ccbaaa72bb02285140589c80fa7859fc


				<li
					class="page-item <c:if test='${currentPage == totalPages}'>disabled</c:if>">
					<a class="page-link" href="/cs/cs?page=${currentPage + 1}">▶</a>
				</li>
			</ul>
>>>>>>> 85fbbfca8fa10b8f4ec427f75e731d5c65e50068
        </div>
    </div>
</div>

<%@ include file="../layout/footer.jsp" %>