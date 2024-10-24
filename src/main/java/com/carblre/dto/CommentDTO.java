package com.carblre.dto;


import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CommentDTO {

    private int commentId;
    private int postId;
    private String userName;
    private int userId;
    private String comment;
    private Timestamp creatAt; // TODO! createdAt 으로 수정 요청 !


    public  CommentDTO toBoardComment(int userId){
        return CommentDTO.builder()
                .commentId(commentId)
                .postId(postId)
                .userName(userName)
                .userId(userId)
                .comment(comment)
                .creatAt(creatAt)
                .build();
    }
}
