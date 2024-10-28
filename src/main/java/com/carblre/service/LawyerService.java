package com.carblre.service;

import com.carblre.dto.LawyerDetailDTO;
import com.carblre.repository.interfaces.LawyerRepository;
import com.carblre.repository.model.LawyerDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class LawyerService {

    @Autowired
    private final LawyerRepository lawyerRepository;

    /**
     * 변호사 리스트 조회
     * @return
     */
    public List<LawyerDetailDTO> LawyerList(){
        List<LawyerDetailDTO> lawyers = new ArrayList<>();
        try {
            lawyers = lawyerRepository.getAllLawyers();
        }catch (Exception e){

        }
        return lawyers;
    }

    /**
     * 변호사 상세보기 페이지 조회
     * @param userId
     * @return
     */
    public LawyerDetailDTO selectByLawyerId(int userId){
        LawyerDetailDTO lawyerDetailDTO = new LawyerDetailDTO();

        lawyerDetailDTO = lawyerRepository.getLawyerById(userId);
        return lawyerDetailDTO;

    }


}
