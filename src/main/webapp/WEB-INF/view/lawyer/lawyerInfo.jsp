<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="/css/lawyerInfo.css">

<div class="wrap">
    <!-- 상단 이미지 & 서브 페이지 이동 START -->
    <section class="sub--section">
        <div class="section--background" id="notice--background">
            <div class="inner--container">
                <sub class="eng">Lawyer</sub>
                <h1 class="sub--title">변호사</h1>
            </div>
        </div>
        <div style="border-bottom: 1px solid #bababa;">
            <div class="inner--container">
                <ul class="sub--top--menu">
                    <li><a href="/board/boardList">의뢰자</a></li>
                    <li><a href="/aiounseling">AI 간편상담</a></li>
                    <li class="subtop--active"><a href="/lawyer/lawyers">변호사</a></li>
                    <li><a href="/notice/notice">공지사항</a></li>
                    <li><a href="/cs/cs">고객센터</a></li>
                </ul>
            </div>
        </div>
    </section>
    <!-- 상단 이미지 & 서브 페이지 이동 END -->

    <div class="inner--container">
        <div class="sub--content">
            <h2 class="prih2"> 변호사 정보 </h2>

            <div class="lawyer-details">
                <img src="/image/lawyer/${lawyer.uploadProfileName}" alt="Profile Image" style="width: 20%; height: auto;" />
                <p>${lawyer.userName} <strong> 변호사</strong></p>
                <p><strong>소개:</strong> ${lawyer.introduction}</p>
                <p><strong>법무법인:</strong> ${lawyer.lawFirm}</p>
                <p><strong>사무실 전화:</strong> ${lawyer.officeNum}</p>
            </div>
            <button onclick="openModal()">상담예약</button>
        </div>
    </div>
</div>

<!-- 예약 모달 -->
<div id="reservationModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2>상담 예약</h2>
        <form action="/counsel/reservation" method="post">
            <label for="date">날짜 선택:</label>
            <input type="date" id="date" name="date" required oninput="formatDate(this)">


            <label for="startTimeHour">시작 시간 선택:</label>
            <select id="startTimeHour" name="startTimeHour">
                <% for (int i = 0; i < 24; i++) { %>
                <option value="<%= i %>"><%= String.format("%02d", i) %></option>
                <% } %>
            </select>
            시
            <label for="endTimeHour">종료 시간 선택:</label>
            <select id="endTimeHour" name="endTimeHour">
                <% for (int i = 0; i < 24; i++) { %>
                <option value="<%= i %>"><%= String.format("%02d", i) %></option>
                <% } %>
            </select>
            시

            <label for="details">상담 내용:</label>
            <textarea id="details" name="details" rows="4" cols="50" placeholder="상담 내용을 입력하세요" required></textarea>

            <button type="submit">예약 제출</button>
        </form>
    </div>
</div>

<%@ include file="../layout/footer.jsp"%>

<script>
    // 모달 열기
    function openModal() {
        document.getElementById("reservationModal").style.display = "block";
    }

    // 모달 닫기
    function closeModal() {
        document.getElementById("reservationModal").style.display = "none";
    }

    // 모달 외부 클릭시 닫기
    window.onclick = function(event) {
        if (event.target == document.getElementById("reservationModal")) {
            closeModal();
        }
    }

    // 날짜 형식 맞추기
    function formatDate(input) {
        const value = input.value;
        if (value) {
            // yyyy-MM-dd 형식을 yyyy/MM/dd로 변경하여 보여주기
            const formattedValue = value.replace(/-/g, '/');
            input.setAttribute('data-date', formattedValue); // 사용자 정의 속성에 형식화된 날짜 저장
        }
    }

    // 날짜 요소에 표시되는 형식을 yyyy/MM/dd로 설정
    document.getElementById('date').addEventListener('input', function () {
        // 아무 값도 선택하지 않았을 때 텍스트를 초기화
        if (!this.value) {
            this.placeholder = "YYYY/MM/DD"; // 기본 플레이스홀더
        }
    });

</script>

<style>
    /* 모달 스타일 */
    .modal {
        display: none;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.5);
    }

    .modal-content {
        background-color: #fefefe;
        margin: 15% auto;
        padding: 20px;
        border: 1px solid #888;
        width: 80%;
        max-width: 500px;
    }

    .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
        cursor: pointer;
    }

    .close:hover,
    .close:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;
    }
</style>