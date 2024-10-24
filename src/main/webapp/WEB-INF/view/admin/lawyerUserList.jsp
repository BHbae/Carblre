<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>회원관리</title>

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
				<h1 class="page-header">법인 회원 관리</h1>
				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li><a href="#">Tables</a></li>
					<li class="active">Data</li>
				</ol>

			</div>

			<div id="page-inner">

				<div class="row">
					<div class="col-md-12">
						<!-- Advanced Tables -->
						<div class="card">
							<div class="card-action">Advanced Tables</div>
							<div class="card-content">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover" id="dataTables-example">
										<thead>
											<tr>
												<th>ID</th>
												<th>이름</th>
												<th>닉네임</th>
												<th>Email</th>
												<th>Phone</th>
												<th>Role</th>
												<th>로펌</th>
												<th>Status</th>
												<th>기능</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="user" items="${lawyerUserList}">
												<tr class="gradeA">
													<td>${user.id}</td>
													<td>${user.userName}</td>
													<td>${user.nickName}</td>
													<td>${user.email}</td>
													<td>${user.phoneNum}</td>
													<td>${user.role}</td>
													<td>${user.lawFirm}</td>
													<td><c:choose>
															<c:when test="${user.status == 0}">승인대기</c:when>
															<c:when test="${user.status == 1}">정상</c:when>
															<c:otherwise>계정정지</c:otherwise>
														</c:choose></td>
													<td><c:choose>
															<c:when test="${user.status == 0}">
																<button class="btn btn-primary" onclick="toggleStatus('${user.id}', 1)">승인</button>
															</c:when>
															<c:when test="${user.status == 1}">
																<button class="btn btn-warning" onclick="toggleStatus('${user.id}', 2)">계정정지</button>
															</c:when>
															<c:otherwise>
																<button class="btn btn-success" onclick="toggleStatus('${user.id}', 1)">정지해제</button>
															</c:otherwise>
														</c:choose></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>

							</div>
						</div>
						<!--End Advanced Tables -->
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
			$(document).ready(function() {
				$('#dataTables-example').dataTable();
			});

			function toggleStatus(userId, newStatus) {
				if (confirm('정말 변경하시겠습니까?')) {
					$.ajax({
						type : 'POST',
						url : '/admin/user-status',
						data : {
							id : userId,
							status : newStatus
						},
						success : function(response) {
							alert(response);
							// 성공적으로 상태가 변경되었을 때의 처리
							location.reload(); // 페이지 새로고침
						},
						error : function(error) {
							alert('오류가 발생했습니다. 다시 시도해 주세요.');
						}
					});
				}
			}
		</script>
		<!-- Custom Js -->
		<script src="/assets/js/custom-scripts.js"></script>
</body>

</html>
