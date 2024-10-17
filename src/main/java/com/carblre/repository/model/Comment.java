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
    private int user_id;
    private int post_id;
    private String comment;
    private Timestamp createAt;

}
