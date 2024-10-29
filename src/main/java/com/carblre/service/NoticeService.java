package com.carblre.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carblre.repository.interfaces.NoticeRepository;
import com.carblre.repository.model.Notice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeRepository noticeRepository;

	// 모든 공지사항 조회
	@Transactional
	public List<Notice> readAllNotice() {
		return noticeRepository.readAllNotice();
	}

	// 번호로 공지사항 조회
	@Transactional
	public Notice readNoticeById(int id) {
		return noticeRepository.readNoticeById(id);
	}

}
