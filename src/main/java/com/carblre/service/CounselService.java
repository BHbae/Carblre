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

    public MyCounselDTO findMyCounselById(int id){
       Counsel counsel= counselRepository.findCounselOfUserById(id);
       MyCounselDTO myCounselDTO=new MyCounselDTO();
    //깃푸쉬 푸쉬
       return  myCounselDTO;
    }

}
