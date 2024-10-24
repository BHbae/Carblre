package com.carblre.repository.interfaces;

import com.carblre.dto.CommentDTO;
import com.carblre.dto.ReplyCommentDTO;
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

     // 대댓글을 작성하는 메소드입니다.
     int insertReplyComment(ReplyCommentDTO dto);


     // 대댓글 리스트를 불러오는 메소드입니다.
    List<ReplyCommentDTO> selectReplyComments(@Param("id") int postId);

}
