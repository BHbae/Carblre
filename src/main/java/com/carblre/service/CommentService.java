package com.carblre.service;

import com.carblre.dto.CommentDTO;
import com.carblre.dto.ReplyCommentDTO;
import com.carblre.repository.interfaces.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);


    /**
     * 댓글 작성
     * @param commentDTO
     * @param userId
     * @return
     */
    public int writeComment(CommentDTO commentDTO){
        return commentRepository.insertComment(commentDTO);
    }

    public List<CommentDTO> getCommentsByCriteria(int postId , String sortBy){
        return commentRepository.selectCommentsByCriteria(postId, sortBy);
    }

    @Transactional
    public int writeReplyComment(ReplyCommentDTO replyCommentDTO){
        return commentRepository.insertReplyComment(replyCommentDTO);
    }

    @Transactional
    public List<ReplyCommentDTO> getReplyComments(int postId){
        return commentRepository.selectReplyComments(postId);
    }

    /**
     * 대댓글 삭제 기능
     * @param replyId
     * @return
     */
    @Transactional
    public int deleteReply(int replyId){
        return commentRepository.deleteReply(replyId);
    }

    /**
     * 댓글 삭제 기능
     * @param commentId
     * @return
     */
    @Transactional
    public int deleteComment(int commentId){
        return commentRepository.deleteComment(commentId);
    }

}


