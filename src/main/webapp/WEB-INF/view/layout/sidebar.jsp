<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar-default navbar-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav" id="main-menu">

			<li><a
				class="${status == 'dashboard' ? 'active-menu waves-effect waves-dark' : 'waves-effect waves-dark'}"
				href="/admin"><i class="fa fa-dashboard"></i> Dashboard</a></li>
			<li><a href="#" class="waves-effect waves-dark"><i
					class="collapse fa fa-sitemap"></i> 회원 관리<span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="/admin/general-user"
						class=" waves-effect waves-dark">일반 회원</a></li>
					<li><a href="/admin/corporate-user"
						class="waves-effect waves-dark">법인 회원</a></li>
				</ul></li>
			<li><a href="/admin/payment"
				class="${status == 'paymentList' ? 'active-menu waves-effect waves-dark' : 'waves-effect waves-dark'}"><i
					class="fa fa-table"></i> 결제 내역 관리</a></li>
			<li><a href="/admin/board"
				class="${status == 'boardList' ? 'active-menu waves-effect waves-dark' : 'waves-effect waves-dark'}"><i
					class="fa fa-table"></i> 게시글 관리</a></li>
			<li><a href="ui-elements.html" class="waves-effect waves-dark"><i
					class="fa fa-desktop"></i> UI Elements</a></li>
			<li><a href="chart.html" class="waves-effect waves-dark"><i
					class="fa fa-bar-chart-o"></i> Charts</a></li>
			<li><a href="tab-panel.html" class="waves-effect waves-dark"><i
					class="fa fa-qrcode"></i> Tabs & Panels</a></li>

			<li><a href="table.html" class="waves-effect waves-dark"><i
					class="fa fa-table"></i> Responsive Tables</a></li>
			<li><a href="form.html" class="waves-effect waves-dark"><i
					class="fa fa-edit"></i> Forms </a></li>


			<li><a href="#" class="waves-effect waves-dark"><i
					class="fa fa-sitemap"></i> Multi-Level Dropdown<span
					class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="#">Second Level Link</a></li>
					<li><a href="#">Second Level Link</a></li>
					<li><a href="#">Second Level Link<span class="fa arrow"></span></a>
						<ul class="nav nav-third-level">
							<li><a href="#">Third Level Link</a></li>
							<li><a href="#">Third Level Link</a></li>
							<li><a href="#">Third Level Link</a></li>

						</ul></li>
				</ul></li>
			<li><a href="empty.html" class="waves-effect waves-dark"><i
					class="fa fa-fw fa-file"></i> Empty Page</a></li>
		</ul>
	</div>
</nav>

