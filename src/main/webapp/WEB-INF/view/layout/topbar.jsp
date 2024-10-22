<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<nav class="navbar navbar-default top-navbar" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle waves-effect waves-dark"
			data-toggle="collapse" data-target=".sidebar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand waves-effect waves-dark" href="/admin"><i
			class="large material-icons">track_changes</i> <strong>관리자페이지</strong></a>

		<div id="sideNav" href="">
			<i class="material-icons dp48">toc</i>
		</div>
	</div>

	<ul class="nav navbar-top-links navbar-right">
		<li><a class="dropdown-button waves-effect waves-dark" href="#!"
			data-activates="dropdown4"><i class="fa fa-envelope fa-fw"></i> <i
				class="material-icons right">arrow_drop_down</i></a></li>
		<li><a class="dropdown-button waves-effect waves-dark" href="#!"
			data-activates="dropdown3"><i class="fa fa-tasks fa-fw"></i> <i
				class="material-icons right">arrow_drop_down</i></a></li>
		<li><a class="dropdown-button waves-effect waves-dark" href="#!"
			data-activates="dropdown2"><i class="fa fa-bell fa-fw"></i> <i
				class="material-icons right">arrow_drop_down</i></a></li>
		<li><a class="dropdown-button waves-effect waves-dark" href="#!"
			data-activates="dropdown1"><i class="fa fa-user fa-fw"></i> <b>John
					Doe</b> <i class="material-icons right">arrow_drop_down</i></a></li>
	</ul>
</nav>