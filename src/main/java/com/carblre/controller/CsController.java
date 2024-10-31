package com.carblre.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.carblre.dto.CsAllDTO;
import com.carblre.dto.CsFindByIdDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.handler.exception.DataDeliveryException;
import com.carblre.handler.exception.UnAuthorizedException;
import com.carblre.service.CsService;
import com.carblre.utils.Define;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cs")
public class CsController {

	private final CsService csService;

	/**
	 * 고객센터
	 * 
	 * @return
	 */
	@GetMapping("/cs")
	public String csPage(Model model, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "5") int limit) {

		// row의 갯수
		int count = csService.countAllCs();

		// 현재 페이지

		// 총페이지수
		int totalPages = (int) Math.ceil((double) count / (double) limit);

		int offset = (page - 1) * limit;
		if (page <= 1) {
			page = 1;
		} else if (page >= totalPages) {
			page = totalPages;
		}

		// 리스트
		List<CsAllDTO> csList = csService.findAllCs(limit, offset);

		model.addAttribute("csList", csList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);

		return "cs/cs";
	}

	/**
	 * 고객센터 글작성 폼
	 * 
	 * @return
	 */
	@GetMapping("/write")
	public String csWritePage(@SessionAttribute(name = Define.PRINCIPAL, required = false) UserDTO user) {
		if (user == null) {
			throw new UnAuthorizedException(Define.ENTER_YOUR_LOGIN, HttpStatus.UNAUTHORIZED);
		}
		return "cs/write";
	}

	@PostMapping("/created")
	public String postMethodName(@RequestParam(name = "title") String title,
			@RequestParam(name = "content") String content, @SessionAttribute(name = Define.PRINCIPAL) UserDTO user) {
		if (user == null) {
			throw new UnAuthorizedException(Define.ENTER_YOUR_LOGIN, HttpStatus.UNAUTHORIZED);
		}
		if (title.length() > 50) {
			throw new DataDeliveryException(Define.TOO_LONG_TITLE_LENGTH, HttpStatus.BAD_REQUEST);
		}
		if (content.length() > 3000) {
			throw new DataDeliveryException(Define.TOO_LONG_CONTENT_LENGTH, HttpStatus.BAD_REQUEST);
		}

		csService.saveCs(user.getId(), title, content);

		return "redirect:/cs/cs";
	}

	/**
	 * 상세보기
	 */
	@GetMapping("/detail/{id}")
	public String csListPage(@PathVariable(name = "id") int id, Model model,
			@SessionAttribute(name = Define.PRINCIPAL, required = false) UserDTO userDTO) {
		if (userDTO == null) {
			throw new UnAuthorizedException(Define.ENTER_YOUR_LOGIN, HttpStatus.UNAUTHORIZED);
		}

		CsFindByIdDTO dto = csService.findById(id);
		if (userDTO.getId() != dto.getUserId() && !userDTO.getRole().equals("admin")) {
			throw new DataDeliveryException(Define.NOT_CS_AN_USER, HttpStatus.BAD_REQUEST);
		}

		model.addAttribute("dto", dto);

		return "cs/detail";
	}

	/**
	 * 수정하기 폼
	 */
	@GetMapping("/edit/{id}")
	public String getMethodName(@PathVariable(name = "id") int id,
			@SessionAttribute(name = Define.PRINCIPAL) UserDTO user, Model model) {
		CsFindByIdDTO dto = csService.findById(id);

		if (user.getId() != dto.getUserId()) {
			throw new DataDeliveryException(Define.NOT_CS_UPDATE_USER, HttpStatus.BAD_REQUEST);
		}

		if (dto.getResponse() != null) {
			throw new DataDeliveryException(Define.NOT_CS_UPDATE, HttpStatus.BAD_REQUEST);
		}

		model.addAttribute("dto", dto);
		return "cs/edit";
	}

	/**
	 * 수정하는 메서드
	 */
	@PostMapping("/edit/{id}")
	public String edit(@PathVariable(name = "id") int id, @RequestParam(name = "title") String title,
			@RequestParam(name = "content") String content, @SessionAttribute(name = Define.PRINCIPAL) UserDTO user) {
		CsFindByIdDTO dto = csService.findById(id);
		if (user.getId() != dto.getUserId()) {
			throw new DataDeliveryException(Define.NOT_CS_AN_USER, HttpStatus.BAD_REQUEST);
		}
		if (title.length() > 50) {
			throw new DataDeliveryException(Define.TOO_LONG_TITLE_LENGTH, HttpStatus.BAD_REQUEST);
		}
		if (content.length() > 3000) {
			throw new DataDeliveryException(Define.TOO_LONG_CONTENT_LENGTH, HttpStatus.BAD_REQUEST);
		}

		csService.updateByIdAndUserId(id, title, content);

		return "redirect:/cs/cs";

	}

	/**
	 * 답변하기 처리
	 */
	@PostMapping("/reply/{id}")
	public String reply(@PathVariable(name = "id") int id, @RequestParam(name = "response") String response) {
		if (response.length() > 3000) {
			throw new DataDeliveryException(Define.TOO_LONG_CONTENT_LENGTH, HttpStatus.BAD_REQUEST);
		}
		csService.createResponse(id, response);
		return "redirect:/cs/detail/" + id;
	}

}
