package com.carblre.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.carblre.dto.admin.AdminLawyerUserDTO;
import com.carblre.repository.model.AdminCrush;
import com.carblre.repository.model.AdminPost;
import com.carblre.repository.model.AdminUser;

@Mapper
public interface AdminRepository {

	// 전체 일반회원 조회
	public List<AdminUser> readAllGeneralUser();

	// 전체 법인회원 조회
	public List<AdminLawyerUserDTO> readAllCorporateUser();

	// 전체 게시글 조회
	public List<AdminPost> readAllPost();

	// 전체 사고 조회
	public List<AdminCrush> readAllCrush();

	// 게시글 존재 확인
	public boolean existsById(Integer id);

	// 게시글 삭제 처리
	public void deleteById(Integer id);

}
