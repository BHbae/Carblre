package com.carblre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chart")
public class ChartController {

    @GetMapping("/chart")
    public String getChart(){
        System.out.println("chart 시작");
        return "chart/chart";
    }
}
