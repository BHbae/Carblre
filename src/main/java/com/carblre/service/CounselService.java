package com.carblre.service;

import com.carblre.dto.MyCounselDTO;
import com.carblre.repository.interfaces.CounselRepository;
import com.carblre.repository.model.Counsel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
