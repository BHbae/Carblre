package com.carblre.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carblre.dto.admin.AdminLawyerUserDTO;
import com.carblre.dto.admin.AdminPostDTO;
import com.carblre.dto.admin.AdminTossHistoryDTO;
import com.carblre.repository.interfaces.AdminRepository;
import com.carblre.repository.model.AdminCrush;
import com.carblre.repository.model.AdminUser;
import com.carblre.repository.model.Notice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final AdminRepository adminRepository;

	@Transactional
	public List<AdminUser> readAllGeneralUser() {
		return adminRepository.readAllGeneralUser();
	}

	@Transactional
	public List<AdminLawyerUserDTO> readAlllawyerUser() {
		return adminRepository.readAlllawyerUser();
	}

	@Transactional
	public List<AdminPostDTO> readAllPost() {
		return adminRepository.readAllPost();
	}

	@Transactional
	public List<AdminCrush> readAllCrush() {
		return adminRepository.readAllCrush();
	}

	@Transactional
	public boolean deletePostById(Integer id) {
		// 해당 ID의 게시글이 존재하는지 확인
		if (adminRepository.existsById(id)) {
			// 존재하면 삭제
			adminRepository.deleteById(id);
			return true; // 삭제 성공
		}
		return false; // 게시글이 존재하지 않음
	}

	// 유저 계정 정지/해제 기능
	@Transactional
	public boolean updateUserStatus(int id, int status) {
		adminRepository.updateUserStatus(id, status);
		return true; // 업데이트 성공 시 true 반환
	}

	// 게시글 상세보기 페이지
	@Transactional
	public AdminPostDTO readPostById(int id) {
		return adminRepository.readPostById(id);
	}

	// 결제 내역 조회
	@Transactional
	public List<AdminTossHistoryDTO> readAllPayment() {
		return adminRepository.readAllPayment();
	}

	// 일반유저 수 조회
	@Transactional
	public int generalUserCount() {
		return adminRepository.generalUserCount();
	}

	// 법인유저 수 조회
	@Transactional
	public int lawyerUserCount() {
		return adminRepository.lawyerUserCount();
	}

	// 전체 공지사항 조회
	@Transactional
	public List<Notice> readAllNotice() {
		return adminRepository.readAllNotice();
	}

	// 법인유저 대기 수 조회
	@Transactional
	public int WaitingLawyerUserCount() {
		return adminRepository.WaitingLawyerUserCount();
	}

}
