<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>변호사 상세보기</title>

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

<!-- 추가된 스타일 -->
<style>
/* 이미지 확대 모달 스타일 */
.modal {
	display: none;
	position: fixed;
	z-index: 1000;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.8);
	justify-content: center;
	align-items: center;
}

.modal img {
	max-width: 90%;
	max-height: 90%;
}

.image-container {
	display: flex;
	align-items: center;
}

.image-container img {
	margin-right: 20px; /* 이미지 간격 조절 */
}
</style>
</head>
<body>
	<div id="wrapper">
		<%@ include file="../layout/topbar.jsp"%>
		<%@ include file="../layout/dropdown.jsp"%>
		<%@ include file="../layout/sidebar.jsp"%>
		<div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">변호사 정보</h1>
			</div>

			<div id="page-inner">
				<div class="container">
					<div class="card">
						<div class="card-body">
							<div class="image-container">
								<img src="/image/lawyer/${lawyer.uploadProfileName}" alt="Profile Image" style="width: 20%; height: auto;" onclick="openModal(this.src)" /> <img src="/image/lawyer/${lawyer.uploadLicenseName}" alt="License" style="width: 100px; height: auto;" onclick="openModal(this.src)" />
							</div>
							<p>
								<strong>ID:</strong> ${lawyer.id}
							</p>
							<p>
								<strong>이름:</strong> ${lawyer.userName}
							</p>
							<p>
								<strong>닉네임:</strong> ${lawyer.nickName}
							</p>
							<p>
								<strong>Email:</strong> ${lawyer.email}
							</p>
							<p>
								<strong>전화번호:</strong> ${lawyer.phoneNum}
							</p>
							<p>
								<strong>Status:</strong>
								<c:choose>
									<c:when test="${lawyer.status == 0}">승인대기</c:when>
									<c:when test="${lawyer.status == 1}">정상</c:when>
									<c:otherwise>계정정지</c:otherwise>
								</c:choose>
							</p>
							<p>
								<strong>소개:</strong> ${lawyer.introduction}
							</p>
							<p>
								<strong>로펌:</strong> ${lawyer.lawFirm}
							</p>
							<p>
								<strong>사무실 전화번호:</strong> ${lawyer.officeNum}
							</p>
							<p>
								<strong>상담비:</strong> ${lawyer.counselingAmount}
							</p>
							<a href="/admin/lawyer-user" class="btn btn-primary">목록으로 돌아가기</a>
						</div>
					</div>
				</div>
			</div>

			<div id="modal" class="modal" onclick="closeModal()">
				<img id="modalImage" src="" alt="Enlarged Image" />
			</div>

			<footer> </footer>
		</div>
	</div>

	<!-- JS Scripts-->
	<script src="/assets/js/jquery-1.10.2.js"></script>
	<script src="/assets/js/bootstrap.min.js"></script>
	<script src="/assets/materialize/js/materialize.min.js"></script>
	<script src="/assets/js/jquery.metisMenu.js"></script>
	<script src="/assets/js/morris/raphael-2.1.0.min.js"></script>
	<script src="/assets/js/morris/morris.js"></script>
	<script src="/assets/js/easypiechart.js"></script>
	<script src="/assets/js/easypiechart-data.js"></script>
	<script src="/assets/js/Lightweight-Chart/jquery.chart.js"></script>
	<script src="/assets/js/dataTables/jquery.dataTables.js"></script>
	<script src="/assets/js/dataTables/dataTables.bootstrap.js"></script>
	<script src="/assets/js/custom-scripts.js"></script>

	<script>
		function openModal(src) {
			document.getElementById('modalImage').src = src;
			document.getElementById('modal').style.display = 'flex';
		}

		function closeModal() {
			document.getElementById('modal').style.display = 'none';
		}
	</script>
</body>
</html>
