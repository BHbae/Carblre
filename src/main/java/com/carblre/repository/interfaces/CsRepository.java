package com.carblre.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.carblre.dto.CsAllDTO;

@Mapper
public interface CsRepository {
	
	int insetCs(@Param("id")int id,@Param("title")String title,@Param("content")String content);

	List<CsAllDTO> findAllCs(@Param("limit")int limit,@Param("offset")int offset);
	
	int countAllCs();

}
