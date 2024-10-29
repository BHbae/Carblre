package com.carblre.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.carblre.dto.admin.AdminLawyerUserDTO;
import com.carblre.dto.admin.AdminPostDTO;
import com.carblre.dto.admin.AdminTossHistoryDTO;
import com.carblre.repository.model.AdminCrush;
import com.carblre.repository.model.AdminUser;
import com.carblre.repository.model.Notice;

@Mapper
public interface NoticeRepository {

	// 전체 공지사항 조회
	public List<Notice> readAllNotice();

	// 번호로 공지사항 조회
	public Notice readNoticeById(int id);

}
