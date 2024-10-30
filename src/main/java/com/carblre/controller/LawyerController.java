package com.carblre.controller;

import com.carblre.dto.LawyerDetailDTO;
import com.carblre.service.LawyerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.carblre.dto.counsel.CounselDTO;
import com.carblre.dto.userdto.LawyerSignUpDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.handler.exception.UnAuthorizedException;
import com.carblre.service.CounselService;
import com.carblre.service.LawyerService;
import com.carblre.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/lawyer")
public class LawyerController {

	@Autowired
	private final LawyerService lawyerService;

	@Autowired
	private final UserService userService;

	@Autowired
	private final CounselService counselService;

	@Autowired
	private final HttpSession session;

	/**
	 * 변호사 가입페지이 이동
	 *
	 * @return
	 */
	@GetMapping("/lawyerSignUp")
	public String lawyerSignupPage() {
		System.out.println("Here in lawyerSignUpPage(UserController)");
		return "lawyer/lawyerSignup";
	}

	/**
	 * [POST] 변호사 회원가입 로직
	 *
	 * @param lawyerSignUpDTO = 사용자의 입력값
	 * @return signIn.jsp
	 */
	@PostMapping("/lawyerSignUp")
	public String lawyerSignUpProc(LawyerSignUpDTO lawyerSignUpDTO) {
		// HTML required 속성으로 null 체크 X
		lawyerService.createLawyerUser(lawyerSignUpDTO);

		// signIn (Login Page) 이동 처리
		return "redirect:/user/signIn";
	}

	@GetMapping("/myPage")
	public String myPage(Model model) {
		UserDTO userDTO = (UserDTO) session.getAttribute("principal");
		if (userDTO == null) {
			// 엔티티가 존재하지 않을 때 NotFoundException 던짐
			throw new UnAuthorizedException("로그인을 해주세요", HttpStatus.UNAUTHORIZED);
		}
		if (userDTO.getRole().equals("lawyer")) {
			com.carblre.dto.userdto.LawyerDetailDTO lawyerDetailDTO = lawyerService.findLawyerInfoById(userDTO.getId());
			System.out.println(lawyerDetailDTO);
			model.addAttribute("lawyer", lawyerDetailDTO);
		}
		// 유저 인포 해야됨
		return "user/myPage";
	}

	@GetMapping("/lawyers")
	public String getAllLawyers(Model model) {
		List<LawyerDetailDTO> lawyers = lawyerService.LawyerList();

		model.addAttribute("lawyers", lawyers);
		return "lawyer/lawyerList";
	}

	// 변호사 상세보기 페이지 조회
	@GetMapping("/lawyerInfo/{userId}")
	public String LawyerInfoPage(@PathVariable(name = "userId") int userId, Model model) {
		LawyerDetailDTO dto = lawyerService.selectByLawyerId(userId);
		UserDTO principal = (UserDTO) session.getAttribute("principal");

		List<CounselDTO> counselDTO = counselService.getCounselReservationByLawyerId(userId);
		System.out.println("COUNSEL DTO : " + counselDTO);

		// CounselDTO에서 각 예약의 시간 데이터를 추출하여 시작 및 종료 시간을 숫자로 변환하여 모델에 추가
		for (CounselDTO counselItem : counselDTO) {
			int counselStartHour = Integer.parseInt(counselItem.getStartTime().substring(11, 13)); // 예: '2024-10-30
																									// 03:00:00'에서 '03'
																									// 추출
			int counselEndHour = Integer.parseInt(counselItem.getEndTime().substring(11, 13)); // 예: '09' 추출
			model.addAttribute("counselStartHour", counselStartHour);
			model.addAttribute("counselEndHour", counselEndHour);
		}

		model.addAttribute("lawyer", dto);
		model.addAttribute("principal", principal);
		model.addAttribute("counsel", counselDTO);

		return "lawyer/lawyerInfo";
	}

}
