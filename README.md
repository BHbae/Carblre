SpringBoot-Project-카블리
=============
스프링 부트 + 공공데이터 파싱,OpenAI를 활용한 변호사매칭사이트
![Screenshot_5](https://github.com/user-attachments/assets/18a61ee3-0d93-48d8-a267-2c56b46987ae)




🖥️프로젝트 소개
-------------
Spring Boot, MyBatis, JSP, Parsing 을 통한 영화 업데이트 그리고 MySQL을 이용하여 개발된 웹 기반의 영화관 데이트 시스템
영화관 데이트를 목적으로 남, 녀 의 커플 매칭, 상점 및 영화결제 시스템
비동기 처리를 활용한 영화예매 시스템

🕰️개발 기간
-------------
```24.08.20 ~ 24.09.20```

🧑‍🤝‍🧑멤버구성
-------------
<ul>
	<li>팀장  : 변영준 - Database 구축, 영화 정보 API 파싱, 영화 상세보기 구현, 데이팅 상점 및 아이템 사용 구현, 예매 기능 구현</li>
	<li>팀원1 : 김가령 - 극장 페이지 구현, 관람평 기능 구현, 공지사항 페이지 구현</li>
	<li>팀원2 : 김성후 - 영화 페이지 구현, 이벤트 페이지 구현, 마이페이지 및 예매 조회 구현</li>
	<li>팀원3 : 유형정 - CSS 총괄, 회원가입 기능 구현 (SMTP), 로그인 기능 구현(카카오톡, 구글, 네이버), 영화 검색 구현, 슈퍼리스트 구현</li>
	<li>팀원4 : 주윤재 - Database 구축, 관리자 페이지 구현</li>
	<li>팀원5 : 배병호 - Database 구축, 결제 API 구현, 데이트 채팅 구현, 매칭 시스템 구현, 데이트 리스트 구현, 프로필 상세보기 구현</li>
</ul>

⚙️개발 환경
-------------
<ul>
	<li>JAVA 21</li>
	<li>JavaScript</li>
	<li>IDE : STS 4</li>
	<li>Framework : springboot</li>
	<li>Database : MySQL</li>
	<li>SQL Mapper: Mybatis</li>
</ul>

📌주요 기능
-------------
<h5>로그인 - <a href="https://github.com/0jun01/CineDate/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(Login)">상세보기</a></h5>
<ul>
	<li>유효성 검사</li>
	<li>소셜 로그인</li>
	<li>ID찾기, PW찾기</li>
</ul>

<h5>회원가입 - <a href="https://github.com/0jun01/CineDate/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85)">상세보기</a></h5>
<ul>
	<li>ID 중복 체크</li>
	<li>SMTP 이메일 인증</li>
</ul>

<h5>메인 홈페이지 - <a href="https://github.com/0jun01/CineDate/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EB%A9%94%EC%9D%B8-%ED%99%88%ED%8E%98%EC%9D%B4%EC%A7%80)">상세보기</a></h5>
<ul>
		<li>영화 정보 파싱</li>      
</ul>

<h5>극장 - <a href="https://github.com/0jun01/CineDate/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EA%B7%B9%EC%9E%A5)">상세보기</a></h5>
<ul>
		<li>영화 극장</li>      
</ul>

<h5>예매 - <a href="https://github.com/0jun01/CineDate/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EC%98%88%EB%A7%A4)">상세보기</a></h5>
<ul>
		<li>fetch를 이용한 비동기 예매 시스템</li>      
		<li>좌석 선택 시스템</li>
</ul>

<h5>결제 - <a href="https://github.com/0jun01/CineDate/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EA%B2%B0%EC%A0%9C)">상세보기</a></h5>
<ul>
		<li>토스 결제</li>      
</ul>

<h5>매칭 - <a href="https://github.com/0jun01/CineDate/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EB%8D%B0%EC%9D%B4%ED%8A%B8)">상세보기</a></h5>
<ul>
	<li>일반/슈퍼리스트 기능</li>
	<li>매칭리스트</li>
	<li>소켓을 활용한 채팅</li>
	<li>아이템 상점</li>
</ul>

<h5>이벤트 - <a href="https://github.com/0jun01/CineDate/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EC%9D%B4%EB%B2%A4%ED%8A%B8)">상세보기</a></h5>
<ul>
	<li>이벤트 조회</li>
</ul>

<h5>공지사항 - <a href="https://github.com/0jun01/CineDate/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EA%B3%B5%EC%A7%80%EC%82%AC%ED%95%AD)">상세보기</a></h5>
<ul>
	<li>공지사항 조회</li>
</ul>	

<h5>관리자 페이지 - <a href="https://github.com/0jun01/CineDate/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EA%B4%80%EB%A6%AC%EC%9E%90-%ED%8E%98%EC%9D%B4%EC%A7%80)">상세보기</a></h5>
<ul>
	<li>공지사항 CRUD</li>
	<li>Chart.js</li>
	<li>결제내역</li>
	<li>슈퍼리스트 관리</li>
</ul>


ERD 다이어그램 - <a href="https://github.com/0jun01/CineDate/wiki/ERD-%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8">상세보기</a>
-------------
![image](https://github.com/user-attachments/assets/53ba2621-0fd1-47b3-8653-622aa89c39ad)

