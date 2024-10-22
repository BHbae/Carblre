package com.carblre.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carblre.dto.admin.AdminLawyerUserDTO;
import com.carblre.dto.admin.AdminPostDTO;
import com.carblre.repository.model.AdminUser;
import com.carblre.service.AdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	private final AdminService adminService;

	/**
	 * 관리자 메인페이지
	 * 
	 * @return
	 */
	@GetMapping("")
	public String mainPage(Model model) {

		// TODO 관리자 권한 확인

		model.addAttribute("status", "dashboard");

		return "admin/dashboard";
	}

	/**
	 * 일반 유저 관리 페이지
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/general-user")
	public String generalUserListPage(Model model) {
		List<AdminUser> generalUserList = adminService.readAllGeneralUser();

		model.addAttribute("status", "generalUserList");
		model.addAttribute("generalUserList", generalUserList);

		return "admin/generalUserList";
	}

	/**
	 * 법인 유저 관리 페이지
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/corporate-user")
	public String corporateUserListPage(Model model) {
		List<AdminLawyerUserDTO> corporateUserList = adminService.readAllCorporateUser();

		model.addAttribute("status", "corporateUserList");
		model.addAttribute("corporateUserList", corporateUserList);

		return "admin/corporateUserList";
	}

	@GetMapping("payment")
	public String paymentListPage(Model model) {
//		List<LawyerUserDTO> corporateUserList = userService.readAllCorporateUser();
//
		model.addAttribute("status", "paymentList");
//		model.addAttribute("corporateUserList", corporateUserList);

		return "admin/paymentList";
	}

	@GetMapping("board")
	public String boardListPage(Model model) {
		List<AdminPostDTO> postList = adminService.readAllPost();
		model.addAttribute("status", "boardList");
		model.addAttribute("postList", postList);

		return "admin/postList";
	}
}
