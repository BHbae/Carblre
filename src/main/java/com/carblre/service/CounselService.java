package com.carblre.service;

import com.carblre.dto.MyCounselDTO;
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
    public List<MyCounselDTO> findMyCounselByUserId(int id){
       List<Counsel> counsel= counselRepository.findCounselOfUserById(id);
        List<MyCounselDTO> dto=new ArrayList<>();
        System.out.println(counsel);
           for(Counsel coun :counsel){
              dto.add(coun.toMycounselDTO());
           }
       return  dto;
    }

    /**
     *  변호사측 예약현황 체크
     * @param id
     * @return
     */
    public List<MyCounselDTO> findMyCounselByLawyerId(int id) {
        List<Counsel> counselList = counselRepository.findCounselOfLawyerById(id);
        List<MyCounselDTO> myCounselDTOS = new ArrayList<>();

        for (Counsel counsel : counselList) {
            // counsel이 null이 아니고 id가 0인 경우 리스트에 추가하지 않음
            if (counsel != null && counsel.getId() != 0) {
                myCounselDTOS.add(counsel.toMycounselDTO());
            }
        }

        return myCounselDTOS.isEmpty() ? null : myCounselDTOS;
    }

    /**
     *  특정 예약 수정
     * @param id
     * @return
     */
    public MyCounselDTO findCounselOfIdLawyerById(int lawyerId,int id){
       Counsel counsel= counselRepository.findCounselByIdLawyerId(lawyerId,id);

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

    public MyCounselDTO findMyStatusById(int userId,int id){
        Counsel counsel= counselRepository.findStatusById(userId,id);
        return  MyCounselDTO.builder().status(counsel.getStatus()).build();
    }

    /**
     * 유저 예약 취소
     * @param id
     * @param status
     * @return
     */
    public int updateUserStatusById(int userId, int status,int id) {

        return counselRepository.updateUserStatusById(userId, status,id);

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
    public int insertCounselReservation(int id, LawyerReservationDTO dto) {
       Counsel counsel= dto.toCounsel();
       counsel.setUserId(id);
       counsel.setStatus(0);
        System.out.println("counsel:"+counsel);
       return  counselRepository.insertCounsel(counsel);
    }

    public List<LawyerReservationDTO> findReservationsByDateTime(int year, int month, int day, int hour, int minute, int id) {
        return counselRepository.findReservationsByDateTime(year, month, day, hour, minute, id);
    }
}
