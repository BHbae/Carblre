<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<h1>게시글 목록</h1>

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
				<td><a href="/detail/{boards.id}">${boards.title}</a></td>
				<td>${boards.createdAt}</td>
			</tr>
	</c:forEach>
		</table>
</main>




<%@ include file="../layout/footer.jsp"%>