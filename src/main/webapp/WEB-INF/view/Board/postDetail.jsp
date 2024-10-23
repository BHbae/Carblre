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
        getComment('newest');
    };

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

                if(data.comment != null)
                {


                // 받아온 데이터를 동적으로 HTML에 추가
                data.forEach(comment => {
                    const commentDiv = document.createElement('div');
                    console.log('comment', comment);
                    commentDiv.classList.add('comment');

                    // 댓글 내용을 포함한 동적 HTML 요소 생성
                    commentDiv.innerHTML = `
                    <div class="comment-author">`
                        + "닉네임 : "  + comment.userName
                        + `</div>`
                        + `<div class="comment-content">`
                        +  comment.comment
                        + `</div>`;

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
            })
            .catch(error => {
                console.error('Error:', error.message);
            });

    }

</script>
<%@ include file="../layout/footer.jsp"%>