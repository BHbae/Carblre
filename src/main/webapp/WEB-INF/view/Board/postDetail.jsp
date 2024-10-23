<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<h1>게시글 상세보기</h1>
<table>
	<tr>
		<th>제목</th>
		<td><a href="/detail/${post.id}">${post.title}</a></td>
	</tr>
	<tr>
		<th>내용</th>
	</tr>
	<tr>
		<c:if test="${post.uploardFileName != null}">
			<td><video width="600" controls>
					<source src="/video/${post.uploardFileName}" >
				</video></td>
		</c:if>
		<td>${post.content}</td>
	</tr>
</table>
<!-- 댓글 부분 -->
<section class="comments--content">
    <div>
        <div style="font-size: 25px; font-weight: bold; color: #333333;">
            댓글 전체
        </div>
    </div>
0

    <section class="comments--content">
        <form id="commentForm">
            <input type="hidden" id="postId" name="postId" value="${post.id}">
            <textarea id="comment" name="comment" rows="4" cols="50" placeholder="댓글을 입력하세요"></textarea>
            <button type="submit" onclick="addComment()">댓글 입력</button>
        </form>

        <div id="commentSection">
            <!-- 댓글 목록이 여기에 동적으로 표시됩니다 -->
            <c:forEach items="${comments}" var="comment">
                <div>${comment.userName}: ${comment.comment}</div>
            </c:forEach>
        </div>
    </section>

<script src="/js/comment.js"></script>
<%@ include file="../layout/footer.jsp"%>