<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="wrap">
	<!-- 상단 이미지 & 서브 페이지 이동 START -->
	<section class="sub--section">
		<div class="section--background" id="notice--background">
			<div class="inner--container">
				<sub class="eng">Client</sub>
				<h1 class="sub--title">의뢰자</h1>
			</div>
		</div>
		<div style="border-bottom: 1px solid #bababa;">
			<div class="inner--container">
				<ul class="sub--top--menu">
					<li class="subtop--active"><a href="/board/boardList">의뢰자</a></li>
					<li><a href="/aiounseling">AI 간편상담</a></li>
					<li><a href="/lawyer/lawyers">변호사</a></li>
					<li><a href="/notice/notice">공지사항</a></li>
					<li><a href="/cs/cs">고객센터</a></li>
				</ul>
			</div>
		</div>
	</section>
	<!-- 상단 이미지 & 서브 페이지 이동 END -->

	<div class="inner--container">
		<div class="sub--content">
			<h2 class="prih2">의뢰자</h2>

			<form action="/savePost" method="post" enctype="multipart/form-data">
				<div class="selectbox">
					<label for="status">유형</label>
					<select id="status" name="status">
						<option value=1>가해자</option>
						<option value=2>피해자</option>
					</select>

					<label for="category">카테고리</label>
					<select id="category" name="category">
						<option value="차량단독">차량단독</option>
						<option value="차대차">차대차</option>
						<option value="차대보행자">차대보행자</option>
						<option value="차대이륜차">차대이륜차</option>
					</select>
				</div>

				<input type="text" name="title" placeholder="제목을 입력해주세요">
				<textarea rows="20" cols="50" name="content" placeholder="내용을 입력해주세요"></textarea>

				<label style="margin-top: 20px; margin: 15px 3px 5px; display: block">업로드 동영상(20MB 이하)</label>
				<input type="file" name="uploardFileName" style="padding: 5px;">

				<br>
				<div class="btn btn--wrap">
					<div id="list--button">
						<a href="/board/boardList">목록</a>
					</div>
					<div id="enter--button">
						<button type="submit">글쓰기</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>




		





<%@ include file="../layout/footer.jsp"%>