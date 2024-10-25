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
import com.carblre.dto.userdto.UserDTO;
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
	public String postMethodName(@RequestParam(name = "title") String tilte,
			@RequestParam(name = "content") String content, @SessionAttribute(name = Define.PRINCIPAL) UserDTO user) {
		csService.saveCs(user.getId(), tilte, content);

		return "redirect:/cs/cs";
	}

}
