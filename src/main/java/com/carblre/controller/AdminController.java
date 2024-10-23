package com.carblre.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carblre.dto.admin.AdminLawyerUserDTO;
import com.carblre.repository.model.AdminCrush;
import com.carblre.repository.model.AdminPost;
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

	/**
	 * 결제 내역 관리 페이지
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/payment")
	public String paymentListPage(Model model) {
//		List<LawyerUserDTO> corporateUserList = userService.readAllCorporateUser();
//
		model.addAttribute("status", "paymentList");
//		model.addAttribute("corporateUserList", corporateUserList);

		return "admin/paymentList";
	}

	/**
	 * 게시글 관리 페이지
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/posts")
	public String boardListPage(Model model) {
		List<AdminPost> postList = adminService.readAllPost();
		model.addAttribute("status", "postList");
		model.addAttribute("postList", postList);

		return "admin/postList";
	}
	
	// 게시글 상세보기 페이지
	@GetMapping("/posts/{id}")
	public String boardDetailPage(Model model) {
		return "admin/postDetail";
	}

	/**
	 * 게시글 삭제 처리
	 * 
	 * @param postId
	 * @return
	 */
	@DeleteMapping("/posts/{id}")
	@ResponseBody
	public ResponseEntity<Void> deletePost(@PathVariable(name = "id") Integer id) {
		boolean isRemoved = adminService.deletePostById(id);
		if (!isRemoved) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	/**
	 * 사고 관리 페이지
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/crush")
	public String crushListPage(Model model) {
		List<AdminCrush> crushList = adminService.readAllCrush();
		model.addAttribute("status", "crushList");
		model.addAttribute("crushList", crushList);

		return "admin/crushList";
	}
}
