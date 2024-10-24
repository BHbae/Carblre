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

                if (Array.isArray(data) && data.length > 0)
                {
                    console.log('comment is not null');
                    // 받아온 데이터를 동적으로 HTML에 추가
                    data.forEach(comment => {
                        const commentContent = comment.comment;  // 댓글 내용
                        const userName = comment.userName;  // 작성자
                        const commentId = comment.commentId;  // 댓글 ID 등

                        const commentDiv = document.createElement('div');
                        commentDiv.classList.add('comment', 'comment-' + commentId);  // 고유 class 추가

                        // 댓글 내용을 포함한 동적 HTML 요소 생성
                        commentDiv.innerHTML = `
                        <div class="comment--author">` + "닉네임 : "  + userName + `</div>
                        <div class="comment--content">` +  commentContent + `</div>
                        <div><button type="button" onclick="showReplyContent(` + commentId + `)">댓글 달기</button></div>`;

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
        const commentId = ${post.id};
        console.log('commentId : ', commentId);
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
                getComment('newest');
                getReplyComment(${post.id});
            })
            .catch(error => {
                console.error('Error:', error.message);
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
                                    <div class="reply--timestamp" style="color: #d12d2d">작성일 : ` + createdAt + `</div>
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
                console.log(error.message);
            });
    }


</script>

<%@ include file="../layout/footer.jsp"%>