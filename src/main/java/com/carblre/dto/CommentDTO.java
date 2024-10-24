package com.carblre.dto;


import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CommentDTO {
    private int userId;
    private String userName;
    private int postId;
    private String comment;
    private Timestamp createAt;


    public  CommentDTO toBoardComment(){
        return CommentDTO.builder()
                .postId(postId)
                .userId(userId)
                .comment(comment)
                .createAt(createAt)
                .build();
    }
}
