package com.carblre.repository.interfaces;

import com.carblre.dto.userdto.LawyerReservationDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.repository.model.Counsel;
import com.carblre.repository.model.LawyerDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.lang.model.element.Name;
import java.util.List;

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

    List<Counsel> findCounselAll();

    // 변호사 예약 정보 조회
    List<LawyerReservationDTO> findReservation();


    List<LawyerReservationDTO> findReservationsByDateTime(
            @Param("year") int year,
            @Param("month") int month,
            @Param("day") int day,
            @Param("hour") int hour,
            @Param("minute") int minute,
            @Param("id") int id
    );
}
