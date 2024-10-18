package com.carblre.service;

import com.carblre.dto.CommentDTO;
import com.carblre.repository.CommentRepository;
import com.carblre.repository.model.Comment;
import lombok.RequiredArgsConstructor;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private CommentRepository commentRepository;

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);


    /**
     * 댓글 작성
     * @param commentDTO
     * @param userId
     * @return
     */
    public int writeComment(CommentDTO commentDTO , int userId){
        return commentRepository.insertComment(commentDTO.toBoardComment(userId));
    }

    public List<CommentDTO> getCommentsByCriteria(int postId , String sortBy){
        return commentRepository.selectCommentsByCriteria(postId, sortBy);
    }


}


