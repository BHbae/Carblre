// 문서 로드시 실행
document.addEventListener('DOMContentLoaded', function() {
    // 첫 로드 시 최신순으로 댓글 로드
    loadComments('newest');


});

// 댓글 로드
function loadComments(sortBy) {
    const id = document.getElementById('postId').value;
    console.log('Post ID', id);
    console.log('Sort By', sortBy);
    fetch('/detail/comment?id=' + id + '&sortBy=' + sortBy)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok.');
            }
            console.log(response.json());
            return response.json();
        })
        .then(data => {
            const commentSection = document.getElementById("commentSection");
            commentSection.innerHTML = ""; // 기존 댓글 클리어

            data.forEach(comment => {
                const commentItem = document.createElement("div");
                commentItem.innerText = `${comment.userName}: ${comment.comment}`;
                commentSection.appendChild(commentItem);
            });
        })
        .catch(error => console.error('Error fetching comments:', error));
}


function addComment()
{

    const postId = document.getElementById('postId').value;
    const comment = document.getElementById('comment').value;

    const requestBody = {
        "postId" : postId ,
        "comment" : comment

    }
     console.log('requestBody', requestBody);

    fetch('http://localhost:8080/comment', {
        method : 'post',
        headers: {
                'Content-Type': 'application/json' // 추가
            },
        body : JSON.stringify(requestBody)
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok.');
     }

         return response.json();
         })
    .then(data => {
    console.log('data', data);
        alert(data.success);
            })
    .catch(error => {
        alert(error.message);
    })


}
