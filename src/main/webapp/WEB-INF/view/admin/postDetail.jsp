<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>게시글 상세보기</title>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="/assets/materialize/css/materialize.min.css" media="screen,projection" />
<!-- Bootstrap Styles-->
<link href="/assets/css/bootstrap.css" rel="stylesheet" />
<!-- FontAwesome Styles-->
<link href="/assets/css/font-awesome.css" rel="stylesheet" />
<!-- Morris Chart Styles-->
<link href="/assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
<!-- Custom Styles-->
<link href="/assets/css/custom-styles.css" rel="stylesheet" />
<!-- Google Fonts-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="/assets/js/Lightweight-Chart/cssCharts.css">
<link rel="stylesheet" href="/assets/css/customPostDetail.css">
</head>
<body>
	<div id="wrapper">
		<%@ include file="../layout/topbar.jsp"%>
		<!-- Dropdown Structure -->
		<%@ include file="../layout/dropdown.jsp"%>
		<!--/. NAV TOP  -->
		<%@ include file="../layout/sidebar.jsp"%>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">게시글 상세보기</h1>

			</div>

			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="card-content">
								<h4 class="post-title">${post.title}</h4>
								<p class="post-meta">
									<strong>작성자:</strong> <span class="post-author">${post.nickName}</span> | <strong>카테고리:</strong> <span class="post-category">${post.category}</span> | <strong>작성일:</strong> <span class="post-date">${post.createdAt}</span>
								</p>
								<hr>
								<p>
									<strong>내용:</strong>
								</p>
								<div class="post-content">${post.content}</div>
								<hr>
								<p>
									<strong>파일:</strong> <span class="post-file">${post.originFileName}</span>
								</p>
								<p>
									<strong>업로드 파일:</strong>
									<c:if test="${post.uploadFileName != null}">
										<video width="200" controls>
											<source src="/uploardVidio/${post.uploadFileName}">
										</video>
									</c:if>
								</p>

								<div class="button-group">
									<button onclick="deletePost(${post.id})" class="btn btn-danger">삭제하기</button>
									<a href="/admin/posts" class="btn btn-secondary">목록으로 돌아가기</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /. ROW  -->

			<!-- /. ROW  -->

			<footer> </footer>
		</div>
		<!-- /. PAGE INNER  -->
	</div>
	<!-- /. PAGE WRAPPER  -->
	<!-- /. WRAPPER  -->
	<!-- JS Scripts-->


	<!-- jQuery Js -->
	<script src="/assets/js/jquery-1.10.2.js"></script>

	<!-- Bootstrap Js -->
	<script src="/assets/js/bootstrap.min.js"></script>

	<script src="/assets/materialize/js/materialize.min.js"></script>

	<!-- Metis Menu Js -->
	<script src="/assets/js/jquery.metisMenu.js"></script>
	<!-- Morris Chart Js -->
	<script src="/assets/js/morris/raphael-2.1.0.min.js"></script>
	<script src="/assets/js/morris/morris.js"></script>


	<script src="/assets/js/easypiechart.js"></script>
	<script src="/assets/js/easypiechart-data.js"></script>

	<script src="/assets/js/Lightweight-Chart/jquery.chart.js"></script>
	<!-- DATA TABLE SCRIPTS -->
	<script src="/assets/js/dataTables/jquery.dataTables.js"></script>
	<script src="/assets/js/dataTables/dataTables.bootstrap.js"></script>
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
	<!-- Custom Js -->
	<script src="/assets/js/custom-scripts.js"></script>
</body>

</html>
