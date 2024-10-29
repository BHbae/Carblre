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

    <div class="inner--container">
        <div class="sub--content">
            <h2 class="prih2">의뢰자</h2>
            <table class="table">
                <div class="board--info">
                    <div class="board--title">${post.title}</div>
                    <div class="board--detail">
                        <span>작성자: ${post.userName}</span>
                        <span>작성일: ${dto.requestTime}</span>
                    </div>
                </div>
                <div class="board--content">
                    <div>${post.content}</div>
                </div>
            </table>

            <!-- 댓글 부분 -->
            <div class="commentwrap" style="margin-bottom: 10px;">
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
            <section class="comments--content">

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
        </div>
    </div>
</div>


<script>
    window.onload = function () {
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

    function getComment(sortBy) {

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

                if (Array.isArray(data) && data.length > 0) {
                    console.log('comment is not null');
                    // 받아온 데이터를 동적으로 HTML에 추가
                    data.forEach(comment => {
                        const userName = comment.userName;  // 작성자
                        const commentContent = comment.comment;  // 댓글 내용
                        const commentId = comment.commentId;  // 댓글 ID 등
                        const userId = comment.userId;
                        const createdAt = comment.createdAt;

                        const commentDiv = document.createElement('div');
                        commentDiv.classList.add('comment', 'comment-' + commentId);  // 고유 class 추가

                        // 댓글 내용을 포함한 동적 HTML 요소 생성
                        commentDiv.innerHTML = `
                        <div class="reply--infom">
                            <div class="comment--author">닉네임 : ` + userName + `</div>
                            <div class="reply--timestamp">작성일 : ` + createdAt + `</div>
                        </div>
                        <div class="comment--content">` + commentContent + `</div>
                        <div class="replywrap">
                            <button type="button" onclick="deleteComment(` + commentId + ',' + userId + `)" style="margin-left: 5px;">삭제</button>
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

    function addComment() {
        const textArea = document.getElementById('comment');
        textArea.innerHTML = '';
        const commentData = document.getElementById('comment').value;
        const requestData = {
            "postId": ${post.id},
            "comment": commentData,
        }


        fetch('http://localhost:8080/board/comment', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestData)

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
                alert(data.message);
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

    function addReplyComment(commentId) {
        const replyCommentValue = document.getElementById('replyComment-' + commentId).value;

        const replyData = {
            "commentId": commentId,
            "comment": replyCommentValue,
            "postId": ${post.id},
        }

        fetch('http://localhost:8080/board/replyComment', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(replyData)
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
                console.log('-------------DATA-------------')
                console.log('Comments data: ', data);
                console.log('data message : ', data.message);
                console.log('data commentId : ', data.commentId);
                console.log('대댓글 commendId 뽑기 : ', data.commentId);
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
                                    <div class="reply--author">닉네임 : ` + userName + `</div>
                                    <div class="reply--timestamp">작성일 : ` + createdAt + `</div>
                                </div>
                                <div class="reply--content">` + replyContent + `</div>
                            </div>

                                <button type="button" onclick="deleteReply(` + replyId + ',' + userId + `)" class="btn delete-btn">삭제</button>
                            `;

                                // 기존 대댓글을 유지하고, 새로운 대댓글만 추가
                                replyCommentSection.appendChild(replyDiv);
                            }
                        }

                        alert(data.message);
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


    function deleteReply(replyId, userId) {
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
                if (data.message === 'success') {

                }
            })
            .catch(error => {
                console.error(error.message);
                // 오류 처리 로직을 여기에 작성
            });
    }

</script>

<%@ include file="../layout/footer.jsp" %>

