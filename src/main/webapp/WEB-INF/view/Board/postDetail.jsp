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
					<source src="/vidio/${post.uploardFileName}" >
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
    <div class="comments--option">
        <ul style="display: flex; flex-direction: row;">
            <li class="option"><a href="#" onclick="loadComments('newest', event)">▼최신순</a></li>
            <li class="option"><a href="#" onclick="loadComments('oldest', event)">▼등록순</a></li>
            <li class="option"><a href="#" onclick="loadComments('likes', event)">▼추천수</a></li>
        </ul>
    </div>
    <form id="commentForm" method="POST" action="/comment">
        <input type="hidden" name="postId" value="${postId}">
        <input type="hidden" name="userId" value="${principalId}">
        <div class="write--box">
            <textarea id="comment" name="comment" rows="4" cols="50" placeholder="댓글을 입력하세요"></textarea>
            <button type="submit">댓글입력</button>
        </div>
    </form>
    <div id="commentSection">
        <!-- 댓글 목록이 여기에 동적으로 표시됩니다 -->
    </div>
</section>
<script type="text/javascript" src="/js/comment.js"></script>

<%@ include file="../layout/footer.jsp"%>