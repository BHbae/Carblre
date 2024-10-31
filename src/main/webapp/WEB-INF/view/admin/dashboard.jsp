<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>대시보드</title>
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
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
				<h1 class="page-header">Dashboard</h1>

			</div>
			<div id="page-inner">

				<div class="dashboard-cards">
					<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-3">

							<div class="card horizontal cardIcon waves-effect waves-dark">
								<div class="card-image red">
									<i class="material-icons dp48">import_export</i>
								</div>
								<div class="card-stacked red">
									<div class="card-content">
										<h3>${generalUserCount + lawyerUserCount}</h3>
									</div>
									<div class="card-action">
										<strong>총 가입자 수</strong>
									</div>
								</div>
							</div>

						</div>
						<div class="col-xs-12 col-sm-6 col-md-3">

							<div class="card horizontal cardIcon waves-effect waves-dark">
								<div class="card-image orange">
									<i class="material-icons dp48">shopping_cart</i>
								</div>
								<div class="card-stacked orange">
									<div class="card-content">
										<h3>${lawyerUserCount}</h3>
									</div>
									<div class="card-action">
										<strong>변호사 수</strong>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-6 col-md-3">

							<div class="card horizontal cardIcon waves-effect waves-dark">
								<div class="card-image blue">
									<i class="material-icons dp48">equalizer</i>
								</div>
								<div class="card-stacked blue">
									<div class="card-content">
										<h3>50</h3>
									</div>
									<div class="card-action">
										<strong>상담 건수</strong>
									</div>
								</div>
							</div>

						</div>
						<div class="col-xs-12 col-sm-6 col-md-3">

							<div class="card horizontal cardIcon waves-effect waves-dark">
								<div class="card-image green">
									<i class="material-icons dp48">supervisor_account</i>
								</div>
								<div class="card-stacked green">
									<div class="card-content">
										<h3>88,658</h3>
									</div>
									<div class="card-action">
										<strong>총 결제금액</strong>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- <div class="row">
					<div class="col-xs-12 col-sm-12 col-md-7">
						<div class="cirStats">
							<div class="row">
								<div class="col-xs-12 col-sm-6 col-md-6">
									<div class="card-panel text-center">
										<h4>Profit</h4>
										<div class="easypiechart" id="easypiechart-blue" data-percent="82">
											<span class="percent">82%</span>
										</div>
									</div>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-6">
									<div class="card-panel text-center">
										<h4>No. of Visits</h4>
										<div class="easypiechart" id="easypiechart-red" data-percent="46">
											<span class="percent">46%</span>
										</div>
									</div>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-6">
									<div class="card-panel text-center">
										<h4>Customers</h4>
										<div class="easypiechart" id="easypiechart-teal" data-percent="84">
											<span class="percent">84%</span>
										</div>
									</div>
								</div>
								<div class="col-xs-12 col-sm-6 col-md-6">
									<div class="card-panel text-center">
										<h4>Sales</h4>
										<div class="easypiechart" id="easypiechart-orange" data-percent="55">
											<span class="percent">55%</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-5">
						<div class="row">
							<div class="col-xs-12">
								<div class="card">
									<div class="card-image donutpad">
										<div id="morris-line-chart"></div>
									</div>
									<div class="card-action">
											<b>Donut Chart Example</b>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				-->
				<div class="row">
					<!-- <div class="col-md-5">
						<div class="card">
							<div class="card-image">
								<div id="morris-donut-chart"></div>
							</div>
							<div class="card-action">
								<b>Line Chart</b>
							</div>
						</div>
					</div> -->
					<canvas id="myChart" width="300" height="50"></canvas>
					<div class="col-md-4 col-sm-12 col-xs-12">
						<div class="card">
							<div class="card-action">
								<b>Tasks Panel</b>
							</div>
							<div class="card-image">
								<div class="collection">
									<a href="#!" class="collection-item">
										Red<span class="new badge red" data-badge-caption="red">4</span>
									</a>
									<a href="#!" class="collection-item">
										Blue<span class="new badge blue" data-badge-caption="blue">4</span>
									</a>
									<a href="#!" class="collection-item">
										<span class="badge">1</span>Alan
									</a>
									<a href="#!" class="collection-item">
										<span class="new badge">4</span>Alan
									</a>
									<a href="#!" class="collection-item">
										Alan<span class="new badge blue" data-badge-caption="blue">4</span>
									</a>
									<a href="#!" class="collection-item">
										<span class="badge">14</span>Alan
									</a>
									<!-- <a href="#!" class="collection-item">
										Custom Badge Captions<span class="new badge" data-badge-caption="custom caption">4</span>
									</a>
									<a href="#!" class="collection-item">
										Custom Badge Captions<span class="badge" data-badge-caption="custom caption">4</span>
									</a> -->
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-7">
						<div class="card">
							<div class="card-image">
								<div id="morris-bar-chart"></div>
							</div>
							<div class="card-action">
								<b> 2024년 수익</b>
							</div>
						</div>
					</div>
				</div>

				<!-- <div class="row">
					<div class="col-xs-12">
						<div class="card">
							<div class="card-image">
								<div id="morris-area-chart"></div>
							</div>
							<div class="card-action">
								<b>Area Chart</b>
							</div>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-md-12"></div>
				</div> -->
				<div class="row">
					<!-- <div class="col-md-4 col-sm-12 col-xs-12">
						<div class="card">
							<div class="card-action">
								<b>Tasks Panel</b>
							</div>
							<div class="card-image">
								<div class="collection">
									<a href="#!" class="collection-item">Red<span class="new badge red" data-badge-caption="red">4</span></a> <a href="#!" class="collection-item">Blue<span class="new badge blue" data-badge-caption="blue">4</span></a> <a href="#!"
										class="collection-item"><span class="badge">1</span>Alan</a> <a href="#!" class="collection-item"><span class="new badge">4</span>Alan</a> <a href="#!" class="collection-item">Alan<span class="new badge blue" data-badge-caption="blue">4</span></a>
									<a href="#!" class="collection-item"><span class="badge">14</span>Alan</a> <a href="#!" class="collection-item">Custom Badge Captions<span class="new badge" data-badge-caption="custom caption">4</span></a> <a href="#!" class="collection-item">Custom
										Badge Captions<span class="badge" data-badge-caption="custom caption">4</span>
									</a>
								</div>
							</div>
						</div>
					</div> -->

					<!-- <div class="col-md-8 col-sm-12 col-xs-12">
						<div class="card">
							<div class="card-action">
								<b>User List</b>
							</div>
							<div class="card-image">
								<ul class="collection">
									<li class="collection-item avatar"><i class="material-icons circle green">track_changes</i> <span class="title">Title</span>
										<p>
											First Line <br> Second Line
										</p> <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a></li>
									<li class="collection-item avatar"><i class="material-icons circle">folder</i> <span class="title">Title</span>
										<p>
											First Line <br> Second Line
										</p> <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a></li>
									<li class="collection-item avatar"><i class="material-icons circle green">track_changes</i> <span class="title">Title</span>
										<p>
											First Line <br> Second Line
										</p> <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a></li>
									<li class="collection-item avatar"><i class="material-icons circle red">play_arrow</i> <span class="title">Title</span>
										<p>
											First Line <br> Second Line
										</p> <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a></li>
								</ul>
							</div>
						</div>
					</div> -->
				</div>
				<!-- /. ROW  -->
				<!-- <div class="fixed-action-btn horizontal click-to-toggle">
					<a class="btn-floating btn-large red"> <i
						class="material-icons">menu</i>
					</a>
					<ul>
						<li><a class="btn-floating red"><i class="material-icons">track_changes</i></a></li>
						<li><a class="btn-floating yellow darken-1"><i
								class="material-icons">format_quote</i></a></li>
						<li><a class="btn-floating green"><i
								class="material-icons">publish</i></a></li>
						<li><a class="btn-floating blue"><i
								class="material-icons">attach_file</i></a></li>
					</ul>
				</div> -->

				<footer> </footer>
			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->
	</div>
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

	<!-- Custom Js -->
	<script src="/assets/js/custom-scripts.js"></script>

	<script>
		// 차트 데이터와 옵션 설정
		const ctx = document.getElementById('myChart').getContext('2d');
		const myChart = new Chart(ctx,
				{
					type : 'line', // 차트의 종류 ('bar', 'line', 'pie', 'doughnut' 등)
					data : {
						labels : [ '1월', '2월', '3월', '4월', '5월',
								'6월', '7월', '8월', '9월', '10월', '11월', '12월'], // X축 레이블
						datasets : [ {
							label : '2024년 매출액',
							data : [ 12, 19, 3, 5, 2, 3, 1, 2, 3, 4, 5, 6 ], // Y축 데이터
							backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
									'rgba(54, 162, 235, 0.2)',
									'rgba(255, 206, 86, 0.2)',
									'rgba(75, 192, 192, 0.2)',
									'rgba(153, 102, 255, 0.2)',
									'rgba(255, 159, 64, 0.2)' ],
							borderColor : [ 'rgba(255, 99, 132, 1)',
									'rgba(54, 162, 235, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(75, 192, 192, 1)',
									'rgba(153, 102, 255, 1)',
									'rgba(255, 159, 64, 1)' ],
							borderWidth : 5
						} ]
					},
					options : {
						scales : {
							y : {
								beginAtZero : true
							// Y축 0에서 시작
							}
						}
					}
				});
	</script>


</body>

</html>