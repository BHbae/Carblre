<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<h1>게시글 상세보기</h1>
<table>
	<tr>
		<th>제목</th>
		<td>${post.title}</td>
	</tr>
	<tr>
		<th>내용</th>
	</tr>
	<tr>
		<c:if test="${post.uploardFileName != null}">
			<td><video width="600" controls>
					<source src="/uploardVidio/test.mp4" type="video/mp4">
				</video></td>
		</c:if>
		<td>${post.content}</td>
	</tr>
</table>


<%@ include file="../layout/footer.jsp"%>