package com.carblre.controller;

import com.carblre.repository.model.AccidentDamageCount;
import com.carblre.repository.model.DeathToYearCount;
import com.carblre.service.ChartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    private ChartService chartService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/chart")
    public String chartData(Model model) throws JsonProcessingException {
        List<DeathToYearCount> deathChartData = chartService.deathToYearCount();
        String jsonChartData = objectMapper.writeValueAsString(deathChartData);
        System.out.println("chartData 차트 데이터 : " + deathChartData);
        System.out.println("jsonChartData 제이슨 차트 데이터 : " + jsonChartData);

        List<AccidentDamageCount> accidentChartData = chartService.accidentDamageCount();
        String jsonAccidentChartData = objectMapper.writeValueAsString(accidentChartData);
        System.out.println("accidentChartData 차트 데이터 : " + accidentChartData);
        System.out.println("jsonAccidentChartData 제이슨 차트 데이터 : " + jsonAccidentChartData);

        model.addAttribute("deathChartData", jsonChartData);
        model.addAttribute("jsonAccidentChartData", jsonAccidentChartData);
        return "chart/chart";
    }
}
