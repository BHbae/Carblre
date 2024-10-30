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

	// 제목으로 검색
	public List<Notice> findByTitle(@Param("title") String title, @Param("offset") int offset,
			@Param("limit") int limit);

	public int countByTitle(@Param("title") String title);

	// 내용으로 검색
	public List<Notice> findByContent(@Param("content") String content, @Param("offset") int offset,
			@Param("limit") int limit);

	public int countByContent(@Param("content") String content);

	// 제목과 내용 모두 검색
	public List<Notice> findByAll(@Param("query") String query, @Param("offset") int offset, @Param("limit") int limit);

	public int countByAll(@Param("query") String query);
}
