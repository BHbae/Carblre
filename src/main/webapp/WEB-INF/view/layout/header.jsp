<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Carblre 카블리</title>
	<link href="/css/header.css" rel="stylesheet">
	<link href="/css/footer.css" rel="stylesheet">
	<link href="/css/common.css" rel="stylesheet">
	<link href="/css/notice.css" rel="stylesheet">
	<link href="/css/privacy.css" rel="stylesheet">
	<link href="/css/mypage.css" rel="stylesheet">
	<link href="/css/font.css" rel="stylesheet">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
	<script src="/js/jquery-3.6.3.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body>

<header>
	<div id="in--header">
		<div id="logo">
			<a href="/" alt="헤더 로고"><img src="/image/logo.png"></a>
		</div>

		<ul id="menu--container">
			<li>
				<a href="/board/boardList">의뢰자</a>
			</li>
			<li>
				<a href="/aiounseling">AI 간편상담</a>
			</li>
			<li>
				<a href="/reservation/reservation">변호사</a>
			</li>
			<li>
				<a href="/notice/notice">공지사항</a>
			</li>
			<li>
				<a href="/cs/cs">고객센터</a>
			</li>
		</ul>

		<div id="icon--container">
			<ul class="info--container">
			<c:choose>
				<c:when test="${principal != null}">
					<li>
						<a href="/user/logout">로그아웃</a>
					</li>
					<li>
						<a href="/user/myPage">마이페이지</a>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="/user/signIn">로그인</a>
					</li>
					<li>
						<a href="/user/signUp">회원가입</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
			<!--div id="search"></div-->
			<div id="moblie--menu">
				<div></div>
				<div></div>
				<div></div>
			</div>
		</div>
	</div>
</header>

<script>
	$(function () {
		var $header = $('header'); // 헤더를 변수에 넣기
		var $page = $('section'); // 색상이 변할 부분
		var $window = $(window);
		var pageOffsetTop = $page.offset().top; // 색상 변할 부분의 top값 구하기

		// 페이지 로드 시 기본 클래스 초기화
		$header.removeClass('downn');

		$window.resize(function () { // 반응형을 대비하여 리사이즈 시 top값을 다시 계산
			pageOffsetTop = $page.offset().top;
		});

		$window.on('scroll', function () { // 스크롤 시
			var scrolled = $window.scrollTop() >= pageOffsetTop; // 스크롤된 상태; true or false
			$header.toggleClass('downn', scrolled); // 클래스 토글
		});
	});

</script>