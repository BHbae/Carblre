package com.carblre.repository.model;

import lombok.*;
import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Comment {

    private int id;
    private int userId;
    private int postId;
    private String comment;
    private Timestamp createAt;

}
