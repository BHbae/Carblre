<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Track Material Design Bootstrap Admin Template</title>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="/assets/materialize/css/materialize.min.css"
	media="screen,projection" />
<!-- Bootstrap Styles-->
<link href="/assets/css/bootstrap.css" rel="stylesheet" />
<!-- FontAwesome Styles-->
<link href="/assets/css/font-awesome.css" rel="stylesheet" />
<!-- Morris Chart Styles-->
<link href="/assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
<!-- Custom Styles-->
<link href="/assets/css/custom-styles.css" rel="stylesheet" />
<!-- Google Fonts-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
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
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example">
										<thead>
											<tr>
												<th>id</th>
												<th>user_name</th>
												<th>nick_name</th>
												<th>password</th>
												<th>email</th>
												<th>phone_num</th>
												<th>role</th>
												<th>created_at</th>
												<th>introduction</th>
												<th>law_firm</th>
												<th>office_num</th>
												<th>uploard_profile_name</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="user" items="${corporateUserList}">
												<tr class="gradeA">
													<td>${user.id}</td>
													<td>${user.userName}</td>
													<td>${user.nickName}</td>
													<td>${user.password}</td>
													<td>${user.email}</td>
													<td>${user.phoneNum}</td>
													<td>${user.role}</td>
													<td>${user.role}</td>
													<td>${user.introduction}</td>
													<td>${user.lawFirm}</td>
													<td>${user.officeNum}</td>
													<td>${user.uploardProfileName}</td>
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
		</script>
		<!-- Custom Js -->
		<script src="/assets/js/custom-scripts.js"></script>
</body>

</html>
