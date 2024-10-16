package com.carblre.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestBoardRepository {
	
	public int savePost(Post post);
	
	public List<Post> findAllBoard();
	
}
