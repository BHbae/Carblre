package com.carblre.repository.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

	private int id;
	private int userId;
	private int status; //  1. 가해자 , 2.피해자
	private String category;
	private String title;
	private String content;
	private String originFileName; // 업로드 동영상 이름
	private String uploadFileName; // 업로드 동영상 이름
	private Timestamp createdAt;
}
