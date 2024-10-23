<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>게시글 상세보기</title>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="/assets/materialize/css/materialize.min.css"
	media="screen,projection" />
<link href="/assets/css/bootstrap.css" rel="stylesheet" />
<link href="/assets/css/font-awesome.css" rel="stylesheet" />
<link href="/assets/css/custom-styles.css" rel="stylesheet" />
<link href="http://fonts.googleapis.com/css?family=Open+Sans"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="wrapper">
		<%@ include file="../layout/topbar.jsp"%>
		<%@ include file="../layout/dropdown.jsp"%>
		<%@ include file="../layout/sidebar.jsp"%>

		<div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">게시글 상세보기</h1>
				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li><a href="#">Posts</a></li>
					<li class="active">상세보기</li>
				</ol>
			</div>

			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="card-content">
								<h4>${post.title}</h4>
								<p>
									<strong>작성자:</strong> ${post.userId}
								</p>
								<p>
									<strong>상태:</strong> ${post.status}
								</p>
								<p>
									<strong>카테고리:</strong> ${post.category}
								</p>
								<p>
									<strong>내용:</strong>
								</p>
								<p>${post.content}</p>
								<p>
									<strong>파일:</strong> ${post.originFileName}
								</p>
								<p>
									<strong>업로드 파일:</strong> ${post.uploadFileName}
								</p>
								<p>
									<strong>작성일:</strong> ${post.createAt}
								</p>

								<a href="/admin/posts/${post.id}/edit" class="btn btn-primary">수정하기</a>
								<button onclick="deletePost(${post.id})" class="btn btn-danger">삭제하기</button>
								<a href="/admin/posts" class="btn btn-secondary">목록으로 돌아가기</a>
							</div>
						</div>
					</div>
				</div>

				<footer></footer>
			</div>
		</div>

		<script src="/assets/js/jquery-1.10.2.js"></script>
		<script src="/assets/js/bootstrap.min.js"></script>
		<script>
            function deletePost(postId) {
                if (confirm('정말 삭제하시겠습니까?')) {
                    $.ajax({
                        url: '/admin/posts/' + postId,
                        type: 'DELETE',
                        success: function(result) {
                            alert('게시글이 삭제되었습니다.');
                            window.location.href = '/admin/posts'; // 목록 페이지로 이동
                        },
                        error: function(xhr, status, error) {
                            alert('삭제 실패: ' + xhr.responseText);
                        }
                    });
                }
            }
        </script>
	</div>
</body>

</html>
