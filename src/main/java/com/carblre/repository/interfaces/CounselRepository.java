package com.carblre.repository.interfaces;

import com.carblre.repository.model.Counsel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.lang.model.element.Name;

@Mapper
public interface CounselRepository {
    // 유저 예약현황
    Counsel findCounselOfUserById(int userId);

    // 변호사 예약 현황
    Counsel findCounselOfLawyerById(int userId);

    // 변호사 예약 상태변경
    int updateStatusById(@Param("id") int id,@Param("status") int status);

    // id값으로 조회
    Counsel findStatusById(int id);

    int updateUserStatusById(@Param("id") int id,@Param("status") int status);

    int insertCounsel(Counsel counsel);
}
