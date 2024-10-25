package com.carblre.service;

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


    public List<LawyerDetail> LawyerList(){
        List<LawyerDetail> lawyers = new ArrayList<>();
        try {
            lawyers = lawyerRepository.getAllLawyers();
        }catch (Exception e){

        }
        return lawyers;
    }
}
