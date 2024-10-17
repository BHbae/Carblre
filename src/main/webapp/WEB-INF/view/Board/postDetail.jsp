<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>댓글 작성</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>

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

</body>
</html>

<script type="text/javascript" src="/js/comment.js"></script>
