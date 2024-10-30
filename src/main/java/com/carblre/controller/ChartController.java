package com.carblre.controller;

import com.carblre.repository.model.AccidentDamageCount;
import com.carblre.repository.model.DeathToYearCount;
import com.carblre.service.ChartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ChartController {

    @Autowired
    private ChartService chartService;

    @Autowired
    private ObjectMapper objectMapper;

//    @GetMapping("/chart")
//    public String chartData(Model model) throws JsonProcessingException {
//        List<DeathToYearCount> seoulCount = chartService.seoulCounts();
//        String jsonChartData = objectMapper.writeValueAsString(seoulCount);
//        System.out.println("chartData 차트 데이터 : " + seoulCount);
//        System.out.println("jsonChartData 제이슨 차트 데이터 : " + jsonChartData);
//
//        return "chart/chart";
//    }

//    @GetMapping("/count")
//    @ResponseBody
//    public List<DeathToYearCount> countData(){
//        return chartService.seoulCounts();
//    }

//    @GetMapping("/count/{region}")
//    @ResponseBody
//    public List<DeathToYearCount> countData(@PathVariable String region) {
//        return chartService.seoulCounts(region); // 지역에 맞는 데이터 반환
//    }

@GetMapping("/count/{regionCode}")
@ResponseBody
public List<DeathToYearCount> countData(@PathVariable int regionCode) {
    return chartService.seoulCounts(regionCode); // 지역 코드에 맞는 데이터 반환
}



}
