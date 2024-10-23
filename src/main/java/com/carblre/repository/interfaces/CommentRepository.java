package com.carblre.repository.interfaces;

import com.carblre.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentRepository {

    /**
     * 댓글 추가
     * @param dto
     * @return
     */
    int insertComment(CommentDTO dto);


    /**
     * 댓글 정렬
     * 기준 (newest, oldest, likes)
     * @param postId
     * @param sortBy
     * @return 댓글 리스트
     */
     List<CommentDTO> selectCommentsByCriteria(
             @Param("id")int postId,
             @Param("sortBy")String sortBy
     );

}
