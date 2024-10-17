package com.carblre.repository;

import com.carblre.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentRepository {
    // 댓글 추가
    int insertComment(@Param("bookComment") CommentDTO dto);

}
