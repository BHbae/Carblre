<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<h1>게시글 작성</h1>

	<form action="/savePost" method="post" enctype="multipart/form-data">
		
		
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
		
		
		
		<label>제목</label> 
		<input type="text" name="title" value="titleTest">
		
		<br>
		<label>내용</label>
		<textarea rows="20" cols="50" name="content">contentTest</textarea>
		<br>
		
		<label>업로드 동영상(20MB 이하)</label>
		<input type="file" name="uploardFileName">
		
		<br>
		<button  type="submit"> 작성 </button>
		
	</form>
		





<%@ include file="../layout/footer.jsp"%>