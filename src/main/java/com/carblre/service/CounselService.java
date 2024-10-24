package com.carblre.service;

import com.carblre.dto.MyCounselDTO;
import com.carblre.repository.interfaces.CounselRepository;
import com.carblre.repository.model.Counsel;
import com.carblre.repository.model.LawyerDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CounselService {

    private final CounselRepository counselRepository;

    public MyCounselDTO findMyCounselById(int id){
       Counsel counsel= counselRepository.findCounselOfUserById(id);
       MyCounselDTO myCounselDTO=new MyCounselDTO();

       return  myCounselDTO;
    }




}
