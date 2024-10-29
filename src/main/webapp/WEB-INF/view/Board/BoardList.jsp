<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

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
                    <li><a href="/notice/notice">변호사</a></li>
                    <li><a href="/notice/notice">공지사항</a></li>
                    <li><a href="/cs/cs">고객센터</a></li>
                </ul>
            </div>
        </div>
    </section>
    <!-- 상단 이미지 & 서브 페이지 이동 END -->

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
    				<a href="<c:url value='/board/detail/${boards.id}' />">${boards.title}</a>
				</td>
				<td>${boards.createdAt}</td>
			</tr>
	</c:forEach>
		</table>
</main>




<%@ include file="../layout/footer.jsp"%>