package com.carblre.service;

import com.carblre.repository.Interface.ChartRepository;
import com.carblre.repository.model.AccidentDamageCount;
import com.carblre.repository.model.Chart;
import com.carblre.repository.model.DeathToYearCount;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@Service
public class ChartService {

    @Autowired
    private ChartRepository chartRepository;

    private DeathToYearCount deathToYearCount;

    @Transactional
    public List<DeathToYearCount> deathToYearCount() {
        List<DeathToYearCount> result = chartRepository.deathToYearCount();
        System.out.println("년도별 사망 및 부상자 통계 : " + result); // 추가된 로그
        return result;
    }

    @Transactional
    public List<AccidentDamageCount> accidentDamageCount() {
        List<AccidentDamageCount> result = chartRepository.accidentDamageCount();
        System.out.println("가해자 법규 위반별 사망 및 부상자 통계 : " + result); // 추가된 로그
        return result;
    }

}
