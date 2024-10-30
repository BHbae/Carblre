package com.carblre.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	// 검색 기능 추가
	@GetMapping("/search")
	public String searchNotices(@RequestParam(name = "query") String query,
			@RequestParam(name = "type", defaultValue = "all") String type,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size, Model model) {

		List<Notice> noticeList;
		int totalNotices;

		if ("title".equals(type)) {
			noticeList = noticeService.searchByTitle(query, page, size);
			totalNotices = noticeService.countNoticesByTitle(query);
		} else if ("content".equals(type)) {
			noticeList = noticeService.searchByContent(query, page, size);
			totalNotices = noticeService.countNoticesByContent(query);
		} else {
			noticeList = noticeService.searchByAll(query, page, size);
			totalNotices = noticeService.countNoticesByAll(query);
		}

		int totalPages = (int) Math.ceil((double) totalNotices / size);

		model.addAttribute("noticeList", noticeList);
		model.addAttribute("curr	entPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);
		model.addAttribute("query", query);
		model.addAttribute("type", type);

		return "notice/notice";
	}

	// 공지사항 작성 폼 페이지
	@GetMapping("/create")
	public String createNoticePage(Model model) {
		return "notice/createNotice"; // 공지사항 작성 JSP 페이지
	}

	// 공지사항 작성 기능
	@PostMapping("/create")
	public String createNotice(@RequestParam(name = "title") String title,
			@RequestParam(name = "content") String content) {
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		noticeService.createNotice(notice);

		return "redirect:/notice/notice"; // 작성 후 공지사항 리스트로 리다이렉트
	}

	// 공지사항 삭제 기능
	@PostMapping("/delete/{id}")
	public String deleteNotice(@PathVariable(name = "id") int id) {
		noticeService.deleteNotice(id);
		return "redirect:/notice/notice"; // 삭제 후 리스트로 리다이렉트
	}

	// 공지사항 수정 폼 페이지
	@GetMapping("/update/{id}")
	public String updateNoticePage(@PathVariable(name = "id") int id, Model model) {
		Notice notice = noticeService.readNoticeById(id);
		model.addAttribute("notice", notice);
		return "notice/updateNotice"; // 수정 페이지 JSP로 리턴
	}

	// 공지사항 수정 처리
	@PostMapping("/update/{id}")
	public String updateNotice(@ModelAttribute Notice notice) {
		noticeService.updateNotice(notice); // 수정 서비스 호출
		return "redirect:/notice/notice"; // 수정 후 공지사항 리스트로 리다이렉트
	}
}
