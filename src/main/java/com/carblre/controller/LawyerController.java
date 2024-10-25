package com.carblre.controller;

import com.carblre.repository.model.LawyerDetail;
import com.carblre.service.LawyerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class LawyerController {

    @Autowired
    private final LawyerService lawyerService;

    @GetMapping("/lawyers")
    public String getAllLawyers(Model model) {
        List<LawyerDetail> lawyers = lawyerService.LawyerList();
        model.addAttribute("lawyers", lawyers);
        return "lawyerList";
    }


}
