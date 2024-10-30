package com.carblre.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.carblre.repository.model.Notice;
import com.carblre.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;

	/**
	 * 공지 사항 리스트 페이지
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/notices")
	public String noticePage(Model model) {

		List<Notice> noticeList = noticeService.readAllNotice();
		model.addAttribute("noticeList", noticeList);

		return "notice/notice";
	}

	/**
	 * 공지 사항 상세보기 페이지
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/{id}")
	public String noticeDetailPage(@PathVariable(name = "id") int id, Model model) {

		Notice notice = noticeService.readNoticeById(id);
		model.addAttribute("notice", notice);

		return "notice/noticeDetail";
	}

	// 공지사항 페이지, 페이징 처리
	@GetMapping("/notice")
	public String getNotices(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model) {

		List<Notice> noticeList = noticeService.findAllNotice(page, size);
		int totalNotices = noticeService.countNotice();
		int totalPages = (int) Math.ceil((double) totalNotices / size);

		model.addAttribute("noticeList", noticeList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);

		return "notice/notice";
	}

}
