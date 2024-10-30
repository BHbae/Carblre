package com.carblre.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.carblre.dto.CsAllDTO;
import com.carblre.dto.CsFindByIdDTO;

@Mapper
public interface CsRepository {

	int insetCs(@Param("id") int id, @Param("title") String title, @Param("content") String content);

	List<CsAllDTO> findAllCs(@Param("limit") int limit, @Param("offset") int offset);

	int countAllCs();

	CsFindByIdDTO findById(int id);

	int updateByIdAndUserId(@Param("id") int id, @Param("title") String title, @Param("content") String content);

	// 답변하기
	public int createResponse(@Param("id") int id, @Param("response") String response);

}
