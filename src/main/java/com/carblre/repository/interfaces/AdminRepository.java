package com.carblre.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.carblre.dto.admin.AdminLawyerUserDTO;
import com.carblre.dto.admin.AdminPostDTO;
import com.carblre.dto.admin.AdminTossHistoryDTO;
import com.carblre.repository.model.AdminCrush;
import com.carblre.repository.model.AdminUser;

@Mapper
public interface AdminRepository {

	// 전체 일반회원 조회
	public List<AdminUser> readAllGeneralUser();

	// 전체 법인회원 조회
	public List<AdminLawyerUserDTO> readAlllawyerUser();

	// 전체 게시글 조회
	public List<AdminPostDTO> readAllPost();

	// 전체 사고 조회
	public List<AdminCrush> readAllCrush();

	// 게시글 존재 확인
	public boolean existsById(Integer id);

	// 게시글 상세 보기
	public AdminPostDTO readPostById(int id);

	// 게시글 삭제 처리
	public void deleteById(Integer id);

	// 유저 정지/해제 기능
	public void updateUserStatus(@Param("id") int id, @Param("status") int status);

	// 전체 결제 내역 조회
	public List<AdminTossHistoryDTO> readAllPayment();

}
