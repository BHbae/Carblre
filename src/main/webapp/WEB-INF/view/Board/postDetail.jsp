<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>댓글 작성</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script>
           document.addEventListener("DOMContentLoaded", function() {
               const commentCreateBtn = document.querySelector("#comment-create-btn");

               commentCreateBtn.addEventListener("click", function() {
                   const comment = {
                       user_name: document.querySelector("#comment-user-name").innerText,
                       body: document.querySelector("#comment-body").value,
                       article_id: document.querySelector("#post-id").value,
                       user_id: document.querySelector("#logged-in-user-id").value
                   };

                   const url = "/api/articles/" + comment.article_id + "/comments";
                   fetch(url, {
                       method: "POST",
                       body: JSON.stringify(comment),
                       headers: {
                           "Content-Type": "application/json"
                       }
                   })
                   .then(response => {
                       if (!response.ok) {
                           throw new Error('Error saving comment');
                       }
                       return response.json(); // 댓글 데이터 반환
                   })
                   .then(data => {
                       // 새 댓글을 DOM에 추가
                       const commentsList = document.querySelector("#comments-list");
                       const newComment = document.createElement("div");
                       newComment.classList.add("comment", "mb-2");
                       newComment.innerHTML = `
                           <strong>${data.user_name}</strong>: ${data.body}
                       `;
                       commentsList.prepend(newComment); // 새 댓글을 맨 위에 추가
                       document.querySelector("#comment-body").value = ""; // 댓글 입력 필드 초기화
                   })
                   .catch(error => {
                       alert(error.message); // 에러 메시지 출력
                   });
               });
           });
       </script>
</head>
<body>

<div class="container mt-4">
    <div class="card m-2" id="comments-new">
        <div class="card-body">
            <form>
                <!-- 로그인한 사용자의 닉네임 표시 (수정 불가) -->
                <div class="mb-3">
                    <label class="form-label">닉네임</label>
                    <p id="comment-user-name">${userName}</p>
                </div>

                <!-- 댓글 작성 필드 -->
                <div class="mb-3">
                    <label class="form-label">댓글</label>
                    <textarea class="form-control form-control-sm" rows="3" id="comment-body"></textarea>
                </div>

                <!-- 게시글 ID 전달 -->
                <input type="hidden" id="post-id" value="${article.id}">
                <!-- 로그인한 사용자 ID 전달 (히든 필드) -->
                <input type="hidden" id="logged-in-user-id" value="${logged_in_user_id}">

                <!-- 댓글 저장 버튼 -->
                <button type="button" class="btn btn-outline-primary btn-sm" id="comment-create-btn">Enter</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
