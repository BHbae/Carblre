package com.carblre.repository.interfaces;

import com.carblre.dto.counsel.CounselDTO;
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
    List<Counsel> findCounselOfUserById(int lawyerId);

    // 변호사 전체예약 현황
    List<Counsel> findCounselOfLawyerById(int lawyerId);

    // 특정 변호사 예약 현황
    Counsel findCounselByIdLawyerId(@Param("lawyerId")int lawyerId,@Param("id") int id);

    // 변호사 예약 상태변경
    int updateStatusById(@Param("id") int id,@Param("status") int status);

    // id값으로 조회
    Counsel findStatusById(@Param("userId")int userId,@Param("id")int id);

    int updateUserStatusById(@Param("userId") int userId,@Param("status") int status,@Param("id")int id);

    // 예약 하기
    int insertCounsel(CounselDTO counselDTO);

    List<Counsel> findCounselAll();

    // 변호사 예약 정보 조회
    List<LawyerReservationDTO> findReservation();

    // 최초 로딩 시 아이디로 불러오기
    List<CounselDTO> findReservationByLawyerId(@Param("lawyerId") int lawyerId);

    // 비동기 함수 컨택 시 예약 시간 불러오기
    List<CounselDTO> findReservationByLawyerIdAndDate(@Param("lawyerId") int lawyerId, @Param("date") String date);

    List<LawyerReservationDTO> findReservationsByDateTime(
            @Param("year") int year,
            @Param("month") int month,
            @Param("day") int day,
            @Param("hour") int hour,
            @Param("minute") int minute,
            @Param("id") int id
    );
}
