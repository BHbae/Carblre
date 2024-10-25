package com.carblre.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyCommentDTO {

    private Integer replyId; // 대댓글의 ID(reply_comment_tb의 PK)
    private Integer commentId; // 댓글의 아이디
    private Integer postId; // 게시물의 아이디(post_tb의 PK)
    private Integer userId; // 유저 아이디(user_tb의 PK)
    private String userName; // 유저 이름
    private String comment; // 내용
    private String createdAt;

}
