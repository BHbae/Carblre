package com.carblre.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carblre.repository.model.Notice;
import com.carblre.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;

	@GetMapping("/notice")
	public String noticePage(Model model) {

		List<Notice> noticeList = noticeService.readAllNotice();
		model.addAttribute("noticeList", noticeList);

		return "notice/notice";
	}

	@GetMapping("/{id}")
	public String noticeDetailPage(@PathVariable(name = "id") int id, Model model) {

		Notice notice = noticeService.readNoticeById(id);
		model.addAttribute("notice", notice);

		return "notice/noticeDetail";
	}

}
