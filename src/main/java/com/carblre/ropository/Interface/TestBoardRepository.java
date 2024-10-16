package com.carblre.ropository.Interface;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.carblre.ropository.model.Post;

@Mapper
public interface TestBoardRepository {
	
	public int savePost(Post post);
	
	public List<Post> findAllBoard();
	
}
