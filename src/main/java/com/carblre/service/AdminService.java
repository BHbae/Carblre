package com.carblre.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carblre.dto.admin.AdminLawyerUserDTO;
import com.carblre.dto.admin.AdminPostDTO;
import com.carblre.repository.interfaces.AdminRepository;
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

	public List<AdminLawyerUserDTO> readAllCorporateUser() {
		return adminRepository.readAllCorporateUser();
	}

	public List<AdminPostDTO> readAllPost() {
		return adminRepository.readAllPost();
	}

}
