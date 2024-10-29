<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<style>
    /* 기본 스타일 */
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        color: #333333;
        margin: 0;
        padding: 0;
    }

    h1 {
        font-size: 24px;
        font-weight: bold;
        color: #333333;
        margin-bottom: 20px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 30px;
    }

    th {
        text-align: left;
        background-color: #f2f2f2;
        padding: 10px;
    }

    td {
        padding: 10px;
        background-color: #ffffff;
        border-bottom: 1px solid #dddddd;
    }

    /* 댓글 섹션 스타일 */
    .comments--content {
        margin-top: 20px;
        padding: 20px;
        border: 1px solid #e0e0e0;
        border-radius: 8px;
        background-color: #f9f9f9;
    }

    /* 댓글 목록 스타일 */
    .comment {
        margin-top: 15px;
        padding: 10px 0;
        border-bottom: 1px solid #e0e0e0;
    }

    /* 댓글 작성자 스타일 */
    .comment--author {
        font-size: 14px;
        font-weight: bold;
        color: #555;
        margin-bottom: 5px;
    }

    /* 댓글 내용 스타일 */
    .comment--content {
        font-size: 14px;
        color: #333;
        line-height: 1.5;
        word-break: break-word;
    }

    /* 대댓글 목록 스타일 */
    .reply-comment-section {
        margin-left: 50px; /* 대댓글을 원 댓글보다 들여쓰기 */
        padding-top: 10px;
    }

    /* 대댓글 컨테이너 스타일 */
    .reply-comment-container {
        display: flex;
        flex-direction: column;
        margin-bottom: 10px;
        padding: 10px;
        background-color: #f1f1f1;
        border-radius: 8px;
    }

    /* 대댓글 작성자 및 작성일 스타일 */
    .reply--header {
        display: flex;
        justify-content: space-between;
        font-size: 12px;
        color: #888;
        margin-bottom: 5px;
    }

    /* 대댓글 작성자 스타일 */
    .reply--author {
        font-weight: bold;
        color: #666;
    }

    /* 대댓글 작성일 스타일 */
    .reply--timestamp {
        font-size: 12px;
        color: #999;
    }

    /* 대댓글 내용 스타일 */
    .reply--content {
        text-align: left; /* 텍스트를 왼쪽 정렬 */
        font-size: 14px;
        color: #444;
        line-height: 1.5;
        word-break: break-word;
    }

    /* 댓글 달기 버튼 스타일 */
    .comment button, .reply-comment-container button {
        margin-top: 10px;
        background-color: #065fd4;
        color: white;
        border: none;
        padding: 5px 10px;
        border-radius: 4px;
        cursor: pointer;
    }

    .comment button:hover, .reply-comment-container button:hover {
        background-color: #004ba0;
    }
    /* 댓글 작성 텍스트 박스 */
    .write--box {
        display: flex;
        flex-direction: column;
        margin-top: 20px;
    }

    .write--box textarea {
        border: 1px solid #d3d3d3;
        padding: 10px;
        border-radius: 4px;
        font-size: 14px;
        resize: none;
    }

    .write--box button {
        align-self: flex-end;
        margin-top: 10px;
        background-color: #1a73e8;
        color: white;
        border: none;
        padding: 8px 16px;
        border-radius: 4px;
        font-size: 14px;
        cursor: pointer;
    }

    /* 대댓글 영역 */
    .reply-comment-section {
        padding-left: 20px;
        margin-top: 10px;
        border-left: 2px solid #e6e6e6;
    }

    .reply-comment {
        padding: 10px 0;
        border-bottom: 1px solid #e6e6e6;
    }

    .reply--header {
        display: flex;
        justify-content: space-between;
        font-size: 12px;
    }

    .reply--author {
        font-weight: bold;
        color: #606060;
    }

    .reply--timestamp {
        color: #909090;
    }

    .reply--content {
        margin-top: 5px;
        color: #333333;
        font-size: 14px;
    }

    /* 대댓글 작성 박스 */
    .reply--comment--box {
        margin-top: 10px;
        padding-left: 20px;
    }

    .reply--comment--box textarea {
        border: 1px solid #d3d3d3;
        padding: 8px;
        width: 100%;
        border-radius: 4px;
        font-size: 14px;
        resize: none;
    }

    .reply--comment--box button {
        margin-top: 5px;
        background-color: #1a73e8;
        color: white;
        border: none;
        padding: 6px 12px;
        border-radius: 4px;
        font-size: 12px;
        cursor: pointer;
    }

    /* 댓글 영역 아래 공간 확보 */
    .comments--option {
        margin-bottom: 20px;
    }
</style>
<h1>게시글 상세보기</h1>
<table>
	<tr>
		<th>제목</th>
		<td>${post.title}</td>
	</tr>
    <tr>
        <th>작성자</th>
        <td>${post.userName}</td>
    </tr>
	<tr>
		<th>내용</th>
	</tr>
	<tr>
<%--		<c:if test="${post.uploardFileName != null}">--%>
<%--			<td><video width="600" controls>--%>
<%--					<source src="/vidio/${post.uploardFileName}" >--%>
<%--				</video></td>--%>
<%--		</c:if>--%>
		<td>${post.content}</td>
	</tr>
</table>
<!-- 댓글 부분 -->
<section class="comments--content">
    <div>
        <div style="font-size: 25px; font-weight: bold; color: #333333;">
            댓글 전체
        </div>
        <div>
            <select id="myDropdown" onchange="getComment()">
                <option value="newest">최신순</option>
                <option value="oldest">오래된 순</option>
                <option value="option3">Option 3</option>
            </select>
        </div>

    </div>
    <div id="commentSection">
        <!-- 댓글 목록이 여기에 동적으로 표시됩니다 -->
    </div>
    <div id="replyCommentSection">
        <!-- 대댓글 목록이 여기에 동적으로 표시됩니다-->
    </div>
    <div class="comments--option">

    </div>

    <div class="write--box">
        <textarea id="comment" name="comment" rows="4" cols="50" placeholder="댓글을 입력하세요"></textarea>
        <button type="submit" onclick="addComment()">댓글입력</button>
    </div>

</section>
<script>
    window.onload = function()
    {
        refreshCommentsAndReplies();
    };

    function refreshCommentsAndReplies() {
        try {
            // 첫 번째 fetch: 댓글 가져오기
            getComment('newest');
        } catch (error) {
            console.error('댓글 불러오기 중 오류 발생:', error);
        } finally {
            try {
                // 두 번째 fetch: 대댓글 가져오기
                getReplyComment(${post.id});
            } catch (error) {
                console.error('대댓글 불러오기 중 오류 발생:', error);
            }
        }
    }

    function getComment(sortBy)
    {

        console.log('this is document Loaded' + sortBy);

        fetch(`http://localhost:8080/board/comment?id=${post.id}&sortBy=` + sortBy)
            .then(response => {
                if (!response.ok) {
                    return response.json().then(data => {
                        throw new Error(data.message || '알 수 없는 에러가 발생했습니다.');
                    });
                }
                return response.json();
            })
            .then(data => {
                console.log('Comments data: ', data);
                const commentSection = document.getElementById('commentSection');
                commentSection.innerHTML = ''; // 기존 댓글 초기화

                if (Array.isArray(data) && data.length > 0 )
                {
                    console.log('comment is not null');
                    // 받아온 데이터를 동적으로 HTML에 추가
                    data.forEach(comment => {
                        const commentContent = comment.comment;  // 댓글 내용
                        const userName = comment.userName;  // 작성자
                        const userId = comment.userId;
                        const commentId = comment.commentId;  // 댓글 ID 등
                        const createdAt = comment.createdAt;

                        const commentDiv = document.createElement('div');
                        commentDiv.classList.add('comment', 'comment-' + commentId);  // 고유 class 추가

                        // 댓글 내용을 포함한 동적 HTML 요소 생성
                        commentDiv.innerHTML = `
                        <div class="comment--author">닉네임 : ` + userName + `</div>
                        <button type="button" onclick="deleteComment(` + commentId + ',' + userId +  `)" style="margin-left: 5px;">삭제</button>
                        <div class="reply--timestamp" style="color: #d12d2d; margin-left: 15px;">작성일 : ` + createdAt + `</div>
                        <div class="comment--content">` + commentContent + `</div>
                        <div>
                            <button type="button" onclick="showReplyContent(` + commentId + `)">댓글 달기</button>
                        </div>`;

                        // commentSection에 추가
                        commentSection.appendChild(commentDiv);
                    });
                }
            })
            .catch(error => {
                console.log(error.message);
            });

    }

    function addComment()
    {
        const textArea = document.getElementById('comment');
        textArea.innerHTML = '';
        const commentData = document.getElementById('comment').value;
        const requestData = {
            "postId" : ${post.id},
            "comment" : commentData,
        }


        fetch('http://localhost:8080/board/comment', {
            method : 'POST',
            headers : {
                'Content-Type' : 'application/json'
            },
            body : JSON.stringify(requestData)

        })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(data => {
                        throw new Error(data.message || '알 수 없는 에러가 발생했습니다.');
                    });
                }
                return response.json();
            })
            .then(data => {
                console.log('Response data:', data);
                alert('test : ', data.message);
                getComment('newest');
                getReplyComment(${post.id});
            })
            .catch(error => {
                alert(error.message);
                window.location.href = "http://localhost:8080/user/signIn";
            });

    }

    function showReplyContent(commentId) {
        const commentDiv = document.querySelector('.comment-' + commentId);  // commentDiv 찾기
        const existingReplyBox = document.getElementById('replyBox-' + commentId);

        // 기존의 replyBox가 있으면 제거
        if (existingReplyBox) {
            existingReplyBox.remove();
        }

        if (commentDiv) {  // commentDiv가 존재하는지 확인
            const replyCommentDiv = document.createElement('div');
            replyCommentDiv.id = 'replyBox-' + commentId;
            replyCommentDiv.classList.add('reply--comment--box');

            replyCommentDiv.innerHTML = `
            <textarea id="replyComment-` + commentId + `" rows="4" cols="50" placeholder="대댓글을 입력하세요"></textarea>
            <div>
                <button type="button" onclick="addReplyComment(` + commentId + `)">대댓글 달기</button>
            </div>`;

            commentDiv.appendChild(replyCommentDiv);
        } else {
            console.error('commentDiv를 찾을 수 없습니다. commentId:', commentId);
        }
    }

    function addReplyComment(commentId)
    {
        const replyCommentValue = document.getElementById('replyComment-' + commentId).value;

        const replyData = {
            "commentId" : commentId,
            "comment" : replyCommentValue,
            "postId" : ${post.id},
        }

        fetch('http://localhost:8080/board/replyComment', {
            method : 'POST',
            headers : {
                "Content-Type" : "application/json"
            },
            body : JSON.stringify(replyData)
        })
            .then(response => {
                if(!response.ok) {
                    return response.json().then(data => {
                        throw new Error(data.message || '알 수 없는 에러가 발생했습니다.');
                    });
                }
                return response.json();
            })
            .then(data => {
                console.log('-------------DATA-------------')
                console.log('Comments data: ', data);
                console.log('data message : ' , data.message);
                console.log('data commentId : ', data.commentId);
                console.log('대댓글 commendId 뽑기 : ' , data.commentId);
                console.log('getReplyComment 호출 시작');
                getReplyComment(${post.id});

                alert(data.message);

            })
            .catch(error => {
                console.log(error.message);
            });
    }

    function getReplyComment(postId) {
        console.log('getReplyComment postId', postId);
        fetch('http://localhost:8080/board/replyComment?id=' + postId)
            .then(response => {
                if (!response.ok) {
                    return response.json().then(data => {
                        throw new Error(data.message || '알 수 없는 에러가 발생했습니다.');
                    });
                }
                return response.json();
            })
            .then(data => {
                console.log('Reply Comments data: ', data);

                // 대댓글 목록을 댓글 아래에 추가하는 로직
                if (Array.isArray(data) && data.length > 0) {
                    data.forEach(reply => {
                        const userId = reply.userId;
                        const commentId = reply.commentId;
                        const replyId = reply.replyId;  // 대댓글 고유 ID
                        const replyContent = reply.comment;  // 대댓글 내용
                        const userName = reply.userName;  // 작성자
                        const createdAt = reply.createdAt ? reply.createdAt : "작성일 없음";  // 작성일, 없으면 '작성일 없음'으로 표시
                        // 댓글에 해당하는 div 찾기
                        const commentDiv = document.querySelector('.comment-' + commentId);

                        if (commentDiv) {
                            // 대댓글을 추가할 div 찾기 또는 새로 생성
                            let replyCommentSection = commentDiv.querySelector('.reply-comment-section');
                            if (!replyCommentSection) {
                                replyCommentSection = document.createElement('div');
                                replyCommentSection.classList.add('reply-comment-section');
                                commentDiv.appendChild(replyCommentSection);
                            }

                            // 중복된 대댓글이 있는지 확인
                            if (!replyCommentSection.querySelector('.reply-' + replyId)) {
                                // 대댓글을 추가할 div 생성
                                const replyDiv = document.createElement('div');
                                replyDiv.classList.add('reply-comment');
                                replyDiv.classList.add('reply-' + replyId);  // 대댓글 고유 class 추가
                                replyDiv.innerHTML = `
                            <div class="reply-comment-container">
                            <div class="reply--header">
                                <div class="reply--author" style="color: #d12d2d">닉네임 : ` + userName + `</div>
                                <button type="button" onclick="deleteReply(` + replyId + ',' +  userId + `)" style="margin-left: 5px;">삭제</button>
                                <div class="reply--timestamp" style="color: #d12d2d; margin-left: 15px;">작성일 : ` + createdAt + `</div>
                            </div>
                            <div class="reply--content" style="color: #d12d2d">` + replyContent + `</div>
                        </div>`;

                                // 기존 대댓글을 유지하고, 새로운 대댓글만 추가
                                replyCommentSection.appendChild(replyDiv);
                            }
                        }

                    });
                }
            })
            .catch(error => {
                alert(error.message);
                window.location.href = "http://localhost:8080/user/signIn";
            });
    }

    function removeReplyContent(commentId) {
        const existingReplyBox = document.getElementById('replyBox-' + commentId);

        // replyBox가 존재하면 제거
        if (existingReplyBox) {
            existingReplyBox.remove();
        }
    }


    function deleteComment(commentId, userId) {

        fetch('http://localhost:8080/board/deleteComment?commentId=' + commentId + '&userId=' + userId)
            .then(response => {
                if (response.status === 500) {
                    throw new Error('댓글 삭제 실패: 서버 오류가 발생했습니다.');
                }
                return response.text(); // 성공적인 응답일 경우 JSON으로 변환
            })
            .then(data => {
                alert(data.message);
                // 추가적인 성공 처리 로직을 여기에 작성
                refreshCommentsAndReplies();

            })
            .catch(error => {
                console.error(error.message);
                // 오류 처리 로직을 여기에 작성
            });
    }


    function deleteReply(replyId, userId)
    {
        fetch('http://localhost:8080/board/deleteReply?replyId=' + replyId + '&userId=' + userId)
            .then(response => {
                if (response.status === 500) {
                    throw new Error('댓글 삭제 실패: 서버 오류가 발생했습니다.');
                }
                return response.text(); // 성공적인 응답일 경우 JSON으로 변환
            })
            .then(data => {
                alert(data);
                // 추가적인 성공 처리 로직을 여기에 작성
                refreshCommentsAndReplies();
                if(data.message === 'success')
                {

                }
            })
            .catch(error => {
                console.error(error.message);
                // 오류 처리 로직을 여기에 작성
            });
    }

</script>

<%@ include file="../layout/footer.jsp"%>