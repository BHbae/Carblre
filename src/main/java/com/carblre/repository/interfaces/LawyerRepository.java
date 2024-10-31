package com.carblre.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.carblre.dto.LawyerChoiceDTO;
import com.carblre.dto.LawyerDetailDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.repository.model.LawyerDetail;
@Mapper
public interface LawyerRepository {

    // 변호사 디테일 insert
    int insertLawyerDetail(LawyerDetail lawyerDetail);

    // 변호사 정보
    LawyerDetail findLawyerInfoById(int id);

    // 변호사 리스트 조회
    List<LawyerDetailDTO> getAllLawyers();

    // 변호사 상세보기 페이지
    LawyerDetailDTO getLawyerById(@Param("userId") int userId);

    // 변호사 전체조회
    List<UserDTO> findAllLawyer();
    
    int updateAmount(@Param("id")int id,@Param("amount") int amount);

	LawyerChoiceDTO lawyerChoice(int id);
    
    
}
