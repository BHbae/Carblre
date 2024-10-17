package com.carblre.repository;

import java.util.List;

import com.carblre.dto.DetailDTO;
import com.carblre.repository.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TestBoardRepository {
	
	public int savePost(Post post);
	
	public List<Post> findAllBoard();

	public DetailDTO selectByPostId(@Param("id")int id);
}
