package com.carblre.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.carblre.dto.CsAllDTO;
import com.carblre.dto.CsFindByIdDTO;
import com.carblre.repository.model.Notice;

@Mapper
public interface CsRepository {

	int insetCs(@Param("id") int id, @Param("title") String title, @Param("content") String content);

	List<CsAllDTO> findAllCs(@Param("offset") int offset, @Param("limit") int limit);

	int countAllCs();

	// 제목으로 검색
	public List<CsAllDTO> findByTitle(@Param("title") String title, @Param("offset") int offset,
			@Param("limit") int limit);

	public int countByTitle(@Param("title") String title);

	// 내용으로 검색
	public List<CsAllDTO> findByContent(@Param("content") String content, @Param("offset") int offset,
			@Param("limit") int limit);

	public int countByContent(@Param("content") String content);

	// 제목과 내용 모두 검색
	public List<CsAllDTO> findByAll(@Param("query") String query, @Param("offset") int offset, @Param("limit") int limit);

	public int countByAll(@Param("query") String query);

	CsFindByIdDTO findById(int id);

	int updateByIdAndUserId(@Param("id") int id, @Param("title") String title, @Param("content") String content);

	// 답변하기 처리
	public int createResponse(@Param("id") int id, @Param("response") String response);

	// 삭제하기 처리
	public int deleteCsById(int id);

}
