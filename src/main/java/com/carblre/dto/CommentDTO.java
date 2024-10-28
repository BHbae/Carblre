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
<<<<<<< HEAD
    private String createdAt; // TODO! createdAt 으로 수정 요청 !
=======
    private Timestamp creatAt; // TODO! createdAt 으로 수정 요청 !
>>>>>>> 39cb0b06ccbaaa72bb02285140589c80fa7859fc


    public  CommentDTO toBoardComment(int userId){
        return CommentDTO.builder()
                .commentId(commentId)
                .postId(postId)
                .userName(userName)
                .userId(userId)
                .comment(comment)
<<<<<<< HEAD
                .createdAt(createdAt)
=======
                .creatAt(creatAt)
>>>>>>> 39cb0b06ccbaaa72bb02285140589c80fa7859fc
                .build();
    }
}
