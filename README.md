SpringBoot-Project-카블리
=============
스프링 부트 + 공공데이터 파싱,OpenAI를 활용한 변호사매칭사이트
![Screenshot_5](https://github.com/user-attachments/assets/18a61ee3-0d93-48d8-a267-2c56b46987ae)




🖥️프로젝트 소개
-------------
Spring Boot, MyBatis, JSP, Parsing, OpenAI 을 유저 모두가 판사가 되어, 사용자들이 다양한 사건과 사고에 대한 의견을 나누고 평가할 수 있으며, 챗봇을 통해 대략적인 비율을 확인할 수 있는 자동차 블랙박스 리뷰 플랫폼

🕰️개발 기간
-------------
```24.10.14 ~ 24.11.01(총 13일)```

🧑‍🤝‍🧑멤버구성
-------------
<ul>
	<li>팀장  : 변영준 - Database 구축, OpenAI로 챗봇 구현, 공지사항, 고객센터 기능 구현</li>
	<li>팀원1 : 유형정 - 공공데이터 API 파싱 및 데이터 가공, 메인페이지 제작, CSS총괄</li>
	<li>팀원2 : 박태현 - 회원가입 이메일 SMTP 인증 구현, 예약 신청 기능 구현, 대댓글 기능 구현</li>
	<li>팀원3 : 김명기 - 로그인 및 소셜 로그린 기능 구현, 마이페이지 정보 및 수정 기능 구현, 아이디 / 비밀버노 찾기구현, 예약 현환 기능 구현</li>
	<li>팀원4 : 김도아 - 결제 기능 구현, 변호사리스트및 상세 보기 기능 구현</li>
	<li>팀원5 : 석지웅 - 관리자 페이지 구현, 공지사항 작성 기능 구현</li>
</ul>

⚙️개발 환경
-------------
<ul>
	<li>JAVA 21</li>
	<li>JavaScript</li>
	<li>IDE : STS 4, IntelliJ IDEA 2024.2.3</li>
	<li>Framework : springboot</li>
	<li>Database : MySQL</li>
	<li>SQL Mapper: Mybatis</li>
</ul>

📌주요 기능
-------------
<h5>로그인</h5>
<ul>
	<li>유효성 검사</li>
	<li>소셜 로그인</li>
	<li>ID찾기, PW찾기</li>
</ul>

<h5>회원가입 </h5>
<ul>
	<li>ID 중복 체크</li>
	<li>SMTP 이메일 인증</li>
</ul>

<h5>메인 홈페이지</h5>
<ul>
	<li>공공 데이터 파싱 - 지역 사고별 사망률</li>
</ul>

<H5>게시판</H5>
<ul>
	<li>게시글작성 - 동영상업로드</li>
	<li>댓글 작성</li>
	<li>대댓글 작성</li>
</ul>

<h5>AI간편상담</h5>
<ul>
	<li>AI간편상담 - OpenAI를 파인튜닝한 챗봇으로 사고 비율 확인</li>
</ul>


<h5>결제</h5>
<ul>
		<li>토스 결제</li>      
</ul>

<h5>변호사</h5>
<ul>
	<li>변호사 리스트, 변호사 상세보기</li>
	<li>상담예약</li>
</ul>

<h5>관리자 페이지 </h5>
<ul>
	<li>공지사항 CRUD</li>
	<li>Chart.js</li>
	<li>결제내역</li>
</ul>


ERD 다이어그램 
-------------
![image](https://github.com/user-attachments/assets/bec72bee-03e0-4145-a647-b4e260d9ca5b)


