package com.carblre.repository.interfaces;

import java.util.List;

import com.carblre.dto.DetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.carblre.repository.model.Post;

@Mapper
public interface TestBoardRepository {

	public int savePost(Post post);

	public List<Post> findAllBoard();

<<<<<<< HEAD:src/main/java/com/carblre/repository/TestBoardRepository.java
	public DetailDTO selectByPostId(@Param("id")int id);

=======
	public Post findById(@Param("id")int postId);

	public DetailDTO selectByPostId(@Param("id")int postId);
>>>>>>> 70810a80e989db619b3e097e4432bd7d042df5e5:src/main/java/com/carblre/repository/interfaces/TestBoardRepository.java
}
