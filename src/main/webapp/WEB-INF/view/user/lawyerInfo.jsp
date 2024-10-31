<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

  <div class="container">
                <h2>${lawyer.userName} 변호사 정보</h2>
                <div class="lawyer-details">
                    <img src="/image/lawyer/${lawyer.uploadProfileName}" alt="Profile Image" style="width: 20%; height: auto;" />
                    <p>${lawyer.userName} <strong> 변호사</strong></p>
                    <p><strong>소개:</strong> ${lawyer.introduction}</p>
                    <p><strong>법무법인:</strong> ${lawyer.lawFirm}</p>
                    <p><strong>사무실 전화:</strong> ${lawyer.officeNum}</p>
                </div>
                    <button>상담예약</button>
            </div>


<%@ include file="../layout/footer.jsp"%>