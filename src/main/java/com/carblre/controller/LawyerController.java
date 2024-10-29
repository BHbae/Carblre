package com.carblre.controller;

import com.carblre.dto.LawyerDetailDTO;
import com.carblre.service.LawyerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/lawyer")
public class LawyerController {

    @Autowired
    private final LawyerService lawyerService;

    @GetMapping("/lawyers")
    public String getAllLawyers(Model model) {
        List<LawyerDetailDTO> lawyers = lawyerService.LawyerList();

        model.addAttribute("lawyers", lawyers);
        return "lawyer/lawyerList";
    }

    // 변호사 상세보기 페이지 조회
    @GetMapping("/lawyerInfo/{userId}")
    public String LawyerInfoPage(@PathVariable(name = "userId") int userId , Model model){
        LawyerDetailDTO dto = lawyerService.selectByLawyerId(userId);
        model.addAttribute("lawyer" ,dto);
        return "lawyer/lawyerInfo";
    }

}
