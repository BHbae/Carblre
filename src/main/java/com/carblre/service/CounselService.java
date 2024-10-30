package com.carblre.service;

import com.carblre.dto.MyCounselDTO;
import com.carblre.dto.counsel.CounselDTO;
import com.carblre.dto.userdto.LawyerDetailDTO;
import com.carblre.dto.userdto.LawyerReservationDTO;
import com.carblre.dto.userdto.LawyerSignUpDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.repository.interfaces.CounselRepository;
import com.carblre.repository.model.Counsel;
import com.carblre.repository.model.LawyerDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CounselService {

    private final CounselRepository counselRepository;

    /**
     *  유저측 예약현황 체크
     * @param id //userId
     * @return
     */
    public MyCounselDTO findMyCounselByUserId(int id){
       Counsel counsel= counselRepository.findCounselOfUserById(id);
        System.out.println(counsel);
       return  counsel.toMycounselDTO();
    }

    /**
     *  변호사측 예약현황 체크
     * @param id
     * @return
     */
    public MyCounselDTO findMyCounselByLawyerId(int id) {
        Counsel counsel= counselRepository.findCounselOfLawyerById(id);
        if(counsel==null){
            return  new MyCounselDTO();
        }
        return  counsel.toMycounselDTO();
    }

    /**
     * 변호사의 예약 상태변경
     * @param id
     * @param status
     * @return
     */
    public int updateStatusById(int id,int status) {

        return counselRepository.updateStatusById(id,status);
    }

    public MyCounselDTO findMyStatusById(int id){
        Counsel counsel= counselRepository.findStatusById(id);
        return  MyCounselDTO.builder().status(counsel.getStatus()).build();
    }

    /**
     * 유저 예약 취소
     * @param id
     * @param status
     * @return
     */
    public int updateUserStatusById(int id, int status) {

        return counselRepository.updateUserStatusById( id, status);

    }


    /**
     *  전체 예약현황 조회
     * @return
     */
    public List<LawyerReservationDTO> findCounselAll() {
        List<LawyerReservationDTO> reservationDTOList=new ArrayList<>();
        List<Counsel> counsel=counselRepository.findCounselAll();
        for(Counsel list : counsel){
            reservationDTOList.add(list.toReservationDTO());
        }
        return reservationDTOList;
    }

    public List<LawyerReservationDTO> findReservation() {

        return counselRepository.findReservation();
    }

    /**
     *  변호 상담 예약
     * @param id
     * @param dto
     * @return
     */
    public int insertCounselReservation(CounselDTO counselDTO)
    {
        int result = counselRepository.insertCounsel(counselDTO);

        if(result != 1)
        {
            return 0;
        }
        return 1;
    }

    /**
     * 특정 변호사의 예약 정보 불러오기
     * @param id
     * @return
     */
    public List<CounselDTO> getCounselReservationByLawyerId(int id) {
        return counselRepository.findReservationByLawyerId(id);
    }
}
