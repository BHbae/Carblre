package com.carblre.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carblre.dto.admin.AdminLawyerUserDTO;
import com.carblre.repository.interfaces.AdminRepository;
import com.carblre.repository.model.AdminCrush;
import com.carblre.repository.model.AdminPost;
import com.carblre.repository.model.AdminUser;

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
	public List<AdminLawyerUserDTO> readAllCorporateUser() {
		return adminRepository.readAllCorporateUser();
	}

	@Transactional
	public List<AdminPost> readAllPost() {
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

}
