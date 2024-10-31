<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carblre 카블리</title>
    <link rel="icon" type="image/png" sizes="32x32" href="/image/favicon.ico">
    <link href="/css/header.css" rel="stylesheet">
    <link href="/css/footer.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
    <link href="/css/signin.css" rel="stylesheet">
    <link href="/css/notice.css" rel="stylesheet">
    <link href="/css/privacy.css" rel="stylesheet">
    <link href="/css/mypage.css" rel="stylesheet">
    <link href="/css/lawyerList.css" rel="stylesheet">
    <link href="/css/lawyerInfo.css" rel="stylesheet">
    <link href="/css/font.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
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
                <a href="/lawyer/lawyers">변호사</a>
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
                            <a href="/user/selectSignUp">회원가입</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
            <!--div id="search"></div-->
            <div class="hd-menubtn menubtn">
                <div class="m_menu">
                    <div class="menubar">
                        <span class="bar bar01"></span>
                        <span class="bar bar02"></span>
                        <span class="bar bar03"></span>
                    </div>
                    <div class="gnb-all">
                        <div class="all-bg"></div>
                        <div class="all-menu">
                            <ul>
                                <li>
                                    <a href="/board/boardList">의뢰자</a>
                                </li>
                                <li>
                                    <a href="/aiounseling">AI 간편상담</a>
                                </li>
                                <li>
                                    <a href="/lawyer/lawyers">변호사</a>
                                </li>
                                <li>
                                    <a href="/notice/notice">공지사항</a>
                                </li>
                                <li>
                                    <a href="/cs/cs">고객센터</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
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


    $(document).ready(function () {
        $(".hd-search").click(function () {
            $("header").toggleClass("top");
            $(".hd-search-box").toggleClass("hd-sch-open");
            $(".hd-search").toggleClass("close");
            $(".hd-logo").toggleClass("show");
            $("html,body").toggleClass("scr-none");
        });
        $("header .gnb-wrap .gnb > li > ul").hide();
        $("header .gnb-wrap .gnb > li").mouseover(function () {
            $("header .gnb-wrap .gnb > li").removeClass("on");
            $("header .gnb-wrap .gnb > li > ul").stop().fadeOut(200);
            $(this).addClass("on");
            $(this).find("ul").stop().fadeIn(200);
        });
        $("header").mouseleave(function () {
            $("header .gnb-wrap .gnb > li").removeClass("on");
            $("header .gnb-wrap .gnb > li > ul").stop().fadeOut(200);
        });
        $(".btn-language").mouseover(function () {
            $(".hd-language ul").stop().fadeIn();
        });
        $(".hd-language").mouseleave(function () {
            $(".hd-language ul").stop().fadeOut();
        });

        if (matchMedia("screen and (max-width: 1920px)").matches) {
            //mobile
            $(".menubar").click(function () {
                $("header").toggleClass("gnb-open");
                $("header").toggleClass("top");
                $(".all-bg").toggle();
                $(".all-menu > ul > li:not(.gnb-sns) > ul").slideUp();
                $(".all-menu > ul > li:not(.gnb-sns) > ul").slideUp();
                $(".all-menu > ul > li:not(.gnb-sns) a").removeClass("on");
            });
            $(".all-menu > ul > li:not(.gnb-sns) > ul").addClass("dep2");
            $(".all-menu > ul > li:not(.gnb-sns) > a").click(function () {
                $(".all-menu > ul > li:not(.gnb-sns) a").removeClass("on");
                $(".all-menu > ul > li:not(.gnb-sns) > ul").slideUp();
                $(".all-menu > ul > li:not(.gnb-sns) > ul.dep2 ul").stop().slideUp();
                if (!$(this).parent().find(".dep2").is(":visible")) {
                    $(this).parent().find(".dep2").stop().slideDown();
                    $(this).addClass("on");
                }
            });
            $(".all-menu > ul > li:not(.gnb-sns) > ul > li > a").click(function () {
                $(".all-menu > ul > li:not(.gnb-sns) > ul > li.dep3 > a").removeClass(
                    "on"
                );
                $(".all-menu > ul > li:not(.gnb-sns) > ul.dep2 ul").stop().slideUp();
                if (!$(this).parent().find("ul").is(":visible")) {
                    $(this).parent().find("ul").stop().slideDown();
                    $(this).addClass("on");
                }
            });
        } else {
            $(".menubar").click(function () {
                //pc
                $("header").toggleClass("gnb-open");
                $("header").toggleClass("top");
                $("html,body").toggleClass("scr-none");
            });
        }
    });
</script>

