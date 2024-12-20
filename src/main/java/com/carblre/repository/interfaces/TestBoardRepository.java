package com.carblre.repository.interfaces;

import java.util.List;

import com.carblre.dto.DetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.carblre.repository.model.Post;

@Mapper
public interface TestBoardRepository {

	public int savePost(Post post);

	public List<Post> findAllBoard(@Param("limit")int limit, @Param("offset")int offset);

	public Post findById(@Param("id")int postId);

	public DetailDTO selectByPostId(@Param("id")int id);

	public int boardAllCount();

}
