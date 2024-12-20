﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>결제관리</title>

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
				<h1 class="page-header">결제내역 관리</h1>

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
												<th>번호</th>
												<th>결제번호</th>
												<th>유저번호</th>
												<th>주문번호</th>
												<th>주문명</th>
												<th>결제금액</th>
												<th>결제요청일</th>
												<th>결제승인일</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="payment" items="${paymentList}">
												<tr class="gradeA">
													<td>${payment.id}</td>
													<td>${payment.paymentKey}</td>
													<td>${payment.userId}</td>
													<td>${payment.orderId}</td>
													<td>${payment.orderName}</td>
													<td>${payment.amount}</td>
													<td>${payment.requestedAt}</td>
													<td>${payment.approvedAt}</td>
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
