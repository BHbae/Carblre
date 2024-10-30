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

	// 페이징 처리
	@Transactional
	public List<Notice> findAllNotice(int page, int size) {
		int offset = (page - 1) * size;
		return noticeRepository.findAllNotice(offset, size);
	}

	// 공지 수 조회
	@Transactional
	public int countNotice() {
		return noticeRepository.countNotice();
	}

	// 제목으로 검색
	@Transactional
	public List<Notice> searchByTitle(String title, int page, int size) {
		int offset = (page - 1) * size;
		return noticeRepository.findByTitle(title, offset, size);
	}

	@Transactional
	public int countNoticesByTitle(String title) {
		return noticeRepository.countByTitle(title);
	}

	// 내용으로 검색
	@Transactional
	public List<Notice> searchByContent(String content, int page, int size) {
		int offset = (page - 1) * size;
		return noticeRepository.findByContent(content, offset, size);
	}

	@Transactional
	public int countNoticesByContent(String content) {
		return noticeRepository.countByContent(content);
	}

	// 제목과 내용 모두 검색
	@Transactional
	public List<Notice> searchByAll(String query, int page, int size) {
		int offset = (page - 1) * size;
		return noticeRepository.findByAll(query, offset, size);
	}

	@Transactional
	public int countNoticesByAll(String query) {
		return noticeRepository.countByAll(query);
	}

	// 공지사항 작성 기능
	@Transactional
	public void createNotice(Notice notice) {
		noticeRepository.createNotice(notice);
	}

	// 공지사항 삭제 기능
	public void deleteNotice(int id) {
		noticeRepository.deleteNotice(id);
	}
	
	// 공지사항 수정 폼 페이지
	public void updateNotice(Notice notice) {
		noticeRepository.updateNotice(notice);
	}
}
