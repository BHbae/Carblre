package com.carblre.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.carblre.repository.model.Notice;

@Mapper
public interface NoticeRepository {

	// 전체 공지사항 조회
	public List<Notice> readAllNotice();

	// 번호로 공지사항 조회
	public Notice readNoticeById(int id);

	// 페이징 처리
	public List<Notice> findAllNotice(@Param("offset") int offset, @Param("limit") int limit);

	// 공지수 조회
	public int countNotice();
}
