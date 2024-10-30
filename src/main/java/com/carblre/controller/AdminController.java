package com.carblre.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;	
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carblre.dto.admin.AdminLawyerUserDTO;
import com.carblre.dto.admin.AdminPostDTO;
import com.carblre.dto.admin.AdminTossHistoryDTO;
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
	@GetMapping("/lawyer-user")
	public String corporateUserListPage(Model model) {
		List<AdminLawyerUserDTO> lawyerUserList = adminService.readAlllawyerUser();

		model.addAttribute("status", "lawyerUserList");
		model.addAttribute("lawyerUserList", lawyerUserList);

		return "admin/lawyerUserList";
	}

	/**
	 * 유저 계정 정지/해제 기능
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@PostMapping("/user-status")
	public ResponseEntity<String> toggleUserStatus(@RequestParam(name = "id") int id,
			@RequestParam(name = "status") int status) {
		System.out.println(" status  : " + status);
		adminService.updateUserStatus(id, status);
		return ResponseEntity.ok("상태 변경 성공");
	}

	/**
	 * 결제 내역 관리 페이지
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/payment")
	public String paymentListPage(Model model) {
		List<AdminTossHistoryDTO> paymentList = adminService.readAllPayment();
//
		model.addAttribute("status", "paymentList");
		model.addAttribute("paymentList", paymentList);

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
		List<AdminPostDTO> postList = adminService.readAllPost();
		model.addAttribute("status", "postList");
		model.addAttribute("postList", postList);

		return "admin/postList";
	}

	// 게시글 상세보기 페이지
	@GetMapping("/posts/{id}")
	public String boardDetailPage(@PathVariable(name = "id") int id, Model model) {
		AdminPostDTO post = adminService.readPostById(id);
		model.addAttribute("status", "postList");
		model.addAttribute("post", post);

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
	 * AI 대화내역 관리 페이지
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/ai-chat")
	public String aiChatListPage(Model model) {
//		List<AdminPost> aiChatList = adminService.readAllPost();
		model.addAttribute("status", "aiChatList");
//		model.addAttribute("aiChatList", aiChatList);

		return "admin/aiChatList";
	}

//	/**
//	 * 사고 관리 페이지
//	 * 
//	 * @param model
//	 * @return
//	 */
//	@GetMapping("/crush")
//	public String crushListPage(Model model) {
//		List<AdminCrush> crushList = adminService.readAllCrush();
//		model.addAttribute("status", "crushList");
//		model.addAttribute("crushList", crushList);
//
//		return "admin/crushList";
//	}
}
