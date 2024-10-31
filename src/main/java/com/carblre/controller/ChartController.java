package com.carblre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carblre.repository.model.DeathToYearCount;
import com.carblre.service.ChartService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/")
public class ChartController {

    @Autowired
    private ChartService chartService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/count/{regionCode}")
    @ResponseBody
    public List<DeathToYearCount> countData(@PathVariable(name = "regionCode") int regionCode) {
        return chartService.seoulCounts(regionCode); // 지역 코드에 맞는 데이터 반환
    }
    
}