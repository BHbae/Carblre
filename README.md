SpringBoot-Project-카블리
=============
스프링 부트 + 공공데이터 파싱,OpenAI를 활용한 변호사매칭사이트
![Screenshot_5](https://github.com/user-attachments/assets/18a61ee3-0d93-48d8-a267-2c56b46987ae)




🖥️프로젝트 소개
-------------
Spring Boot, MyBatis, JSP, Parsing, OpenAI 를 사용한 사이트로 유저 모두가 판사가 되어, 사용자들이 다양한 사건과 사고에 대한 의견을 나누고 평가할 수 있으며, 챗봇을 통해 대략적인 비율을 확인할 수 있는 자동차 블랙박스 리뷰 플랫폼

🕰️개발 기간
-------------
```24.10.14 ~ 24.11.01(총 13일)```

🧑‍🤝‍🧑멤버구성
-------------
<ul>
	<li>팀장 : 배병호 - Database 구축, OpenAI로 챗봇 구현, 공지사항, 고객센터 기능 구현</li>
	<li>팀원 : 유형정 - 공공데이터 API 파싱 및 데이터 가공, 메인페이지 제작, CSS 총괄</li>
	<li>팀원 : 박태현 - 회원가입 이메일 SMTP 인증 구현, coolSMS 문자 인증 기능, 로컬 회원가입 및 로그인, 예약 신청 기능 구현, 대댓글 기능 구현</li>
	<li>팀원 : 김명기 - 로그인 및 소셜 로그인 기능 구현, 마이페이지 정보 및 수정 기능 구현, 아이디 / 비밀버노 찾기구현, 예약 현환 기능 구현</li>
	<li>팀원 : 김도아 - 결제 기능 구현, 변호사리스트및 상세 보기 기능 구현</li>
	<li>팀원 : 석지웅 - 관리자 페이지 구현, 공지사항 작성 기능 구현</li>
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

![로그인](https://github.com/user-attachments/assets/7e3a83e7-5f37-487a-a555-76e3849baf66)



<h5>회원가입 </h5>
<ul>
	<li>ID 중복 체크</li>
	<li>SMTP 이메일 인증</li>
</ul>

![image](https://github.com/user-attachments/assets/8589bf56-7b75-4885-8c9e-58836e0959a4)

<h5>메인 홈페이지</h5>
<ul>
	<li>공공 데이터 파싱 - 지역 사고별 사망률</li>
</ul>

![image](https://github.com/user-attachments/assets/17f9d57b-695e-4734-af22-9f3223f8bfc3)


<H5>게시판</H5>
<ul>
	<li>게시글작성 - 동영상업로드</li>
	<li>댓글 작성</li>
	<li>대댓글 작성</li>
</ul>

![565](https://github.com/user-attachments/assets/f7f9793e-6fe8-4326-bc7a-772721ef9392)


<h5>AI간편상담</h5>
<ul>
	<li>AI간편상담 - OpenAI를 파인튜닝한 챗봇으로 사고 비율 확인</li>
</ul>

![image](https://github.com/user-attachments/assets/3ab8c695-1657-496c-b568-0c38d22420ed)


<h5>결제</h5>
<ul>
		<li>토스 결제</li>      
</ul>

<h5>변호사</h5>
<ul>
	<li>변호사 리스트, 변호사 상세보기</li>
	<li>상담예약</li>
</ul>

![image](https://github.com/user-attachments/assets/2a254ff9-f05e-490e-859d-0233b3bbde8a)


<h5>관리자 페이지 </h5>
<ul>
	<li>공지사항 CRUD</li>
	<li>Chart.js</li>
	<li>결제내역</li>
</ul>

![image](https://github.com/user-attachments/assets/0db2adda-1142-42ed-839a-d2e80219e08b)




<h2>사이트맵</h2>

![image](https://github.com/user-attachments/assets/f4a86723-31aa-458c-8025-4227af8c8736)



ERD 다이어그램 
-------------
![image](https://github.com/user-attachments/assets/bec72bee-03e0-4145-a647-b4e260d9ca5b)


