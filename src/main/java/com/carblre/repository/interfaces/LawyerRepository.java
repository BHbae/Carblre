package com.carblre.repository.interfaces;

import com.carblre.dto.LawyerDetailDTO;
import com.carblre.repository.model.LawyerDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface LawyerRepository {
    // 변호사 리스트 조회
    public List<LawyerDetailDTO> getAllLawyers();

    // 변호사 상세보기 페이지
    LawyerDetailDTO getLawyerById(@Param("userId") int userId);
}
