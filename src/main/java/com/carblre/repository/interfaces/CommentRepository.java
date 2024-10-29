package com.carblre.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.carblre.dto.CommentDTO;
import com.carblre.dto.ReplyCommentDTO;

@Mapper
public interface CommentRepository {

    // 댓글을 작성하는 메소드입니다.
    int insertComment(CommentDTO dto);

    // 댓글을 삭제하는 메소드입니다.
    int deleteComment(@Param("commentId") int commentId);


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

    // 대댓글 삭제하는 메소드입니다.
    int deleteReply(@Param("replyId") int replyId);
}
