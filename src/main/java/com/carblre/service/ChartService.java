package com.carblre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carblre.repository.Interface.ChartRepository;
import com.carblre.repository.model.DeathToYearCount;

import lombok.Data;

@Data
@Service
public class ChartService {

    @Autowired
    private ChartRepository chartRepository;

    private DeathToYearCount deathToYearCount;

    @Transactional
    public List<DeathToYearCount> seoulCounts(int regionCode) {
        List<DeathToYearCount> result = chartRepository.seoulCount(regionCode);
        System.out.println("2023년 시도 법규위반에 따른 사망, 부상, 중상, 경상자수 : " + result); // 추가된 로그
        return result;
    }

}